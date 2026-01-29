import { Component, Input, ViewChild, TemplateRef } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AssessmentService } from '../../services/assessment.service';
import { SharedDataService } from '../../services/shared-data.sevice';
import { Assessment, Course } from '../../home/course.model';
import { AssessmentsComponent } from '../../assessments/assessments.component';
import { NgClass, NgForOf, NgIf } from '@angular/common';
import { ButtonComponent } from '../../shared/button/button.component';
import { ReactiveFormsModule } from '@angular/forms';
import { concatMap, from } from 'rxjs';

@Component({
  selector: 'app-create-assessment',
  templateUrl: './create-assessment.component.html',
  standalone: false,
  styleUrls: ['./create-assessment.component.scss']
})
export class CreateAssessmentComponent {
  @Input() course: Course | null = null;
  @Input() existingTotal=0;
  @ViewChild('addAssessmentModal') addAssessmentModal!: TemplateRef<any>;

  assessmentForm: FormGroup;
  isSubmitted = false;
  pendingAssessments: any[] = [];
  isCommitting = false;

  constructor(
    private sharedDataService: SharedDataService,
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private assessmentService: AssessmentService
  ) {
    this.assessmentForm = this.formBuilder.group({
      categoryOption: new FormControl('Quiz', Validators.required),
      customCategory: new FormControl(''),
      customShortName: new FormControl(''),
      numAssessments: new FormControl(1, [Validators.required, Validators.min(1)]),
      maxPoints: new FormControl(null, [Validators.required, Validators.min(0), Validators.max(100)]),
      isBonus: new FormControl(false)
    });
  }

  openVerticallyCentered(content: any) {
    if (this.modalService.hasOpenModals()) return;
    this.modalService.open(content, {
      backdrop: 'static',
      keyboard: false,
      centered: true
    });
  }

  close() {
    this.modalService.dismissAll();
  }

  getSubmitButtonLabel(): string {
    const total = this.getOverallTotal();
    return total === 100 ? 'Save All' : `Add More (Total: ${total}%)`;
  }

  addCategory() {
    this.isSubmitted = true;
    if (this.assessmentForm.invalid) return;

    const { categoryOption, customCategory, customShortName, numAssessments, maxPoints, isBonus } = this.assessmentForm.value;
    let assessmentCategory: string;
    let baseShortName: string;

    if (categoryOption !== 'Other') {
      assessmentCategory = categoryOption;
      const mapping: { [key: string]: string } = {
        'Quiz': 'QZ',
        'Practical Examination': 'PE',
        'Midterm': 'ME',
        'Final': 'FE'
      };
      baseShortName = mapping[categoryOption];
    } else {
      assessmentCategory = customCategory;
      baseShortName = customShortName;
    }

    const pendingTotal = this.getPendingTotal();
    const overallTotal = this.getOverallTotal();

    if (!isBonus && maxPoints > 40) {
      alert('A single assessment category cannot exceed 40%.');
      return;
    }

    if (!isBonus && overallTotal + maxPoints > 100) {
      alert(`Total assessment weight cannot exceed 100%. Existing: ${this.existingTotal}% , Pending: ${pendingTotal}%`);
          return;
    }

    const weightPerAssessment = parseFloat((maxPoints / numAssessments).toFixed(2));

    // Final shortName for the pending table (preview only)
    const previewShortName = numAssessments > 1
      ? Array.from({ length: numAssessments }, (_, i) => `${baseShortName}${i + 1}`).join(', ')
      : baseShortName;

    const pendingCategory = {
      category: assessmentCategory,
      shortName: previewShortName,
      baseShortName: baseShortName, // Used later for generation
      numAssessments: numAssessments,
      maxPoints: weightPerAssessment,
      totalWeight: maxPoints,
      isBonus: isBonus
    };

    this.pendingAssessments.push(pendingCategory);

    this.assessmentForm.reset({
      categoryOption: 'Quiz',
      customCategory: '',
      customShortName: '',
      numAssessments: 1,
      maxPoints: null,
      isBonus: false
    });
    this.isSubmitted = false;
  }

  removeCategory(index: number) {
    this.pendingAssessments.splice(index, 1);
  }

  commitAssessments() {
    if (this.isCommitting) return;
    this.isCommitting = true;

    const overallTotal = this.getOverallTotal();
    if (overallTotal > 100) {
      alert(`Total weight must be less than 100%. Current total weight is ${overallTotal}%.`);
      this.isCommitting = false;
      return;
    }

    const assessmentPayloads: any[] = [];

    this.pendingAssessments.forEach((category) => {
      for (let i = 0; i < category.numAssessments; i++) {
        const shortName =
          category.numAssessments > 1
            ? `${category.baseShortName}${i + 1}`
            : category.baseShortName;

        const name =
          category.numAssessments > 1
            ? `${category.category} ${i + 1}`
            : category.category;

        const payload = {
          name: name,
          category: category.category,
          shortName: shortName,
          maxPoints: category.maxPoints,
          totalWeight: category.totalWeight,
          isBonus: category.isBonus,
          sectionId: this.course?.sections?.[0].sectionId,
        };
        assessmentPayloads.push(payload);
      }
    });

    let createdCount = 0;

    from(assessmentPayloads)
      .pipe(
        concatMap((payload) => this.assessmentService.createAssessment(payload))
      )
      .subscribe({
        next: (data: Assessment) => {
          this.course?.sections?.[0].assessments.push(data);
          createdCount++;
          if (createdCount === assessmentPayloads.length) {
            this.sharedDataService.setSelectedCourse(this.course);
            this.pendingAssessments = [];
            this.close();
            this.isCommitting = false;
          }
        },
        error: (error) => {
          console.error('Error creating assessment:', error);
          this.isCommitting = false;
        }
      });
  }

  getPendingTotal(): number{
    return this.pendingAssessments.reduce(
      (sum,c)=>sum+(c.isBonus ? 0: c.totalWeight || 0), 0
    );
  }

  getOverallTotal(): number{
    return this.existingTotal + this.getPendingTotal();
  }
}
