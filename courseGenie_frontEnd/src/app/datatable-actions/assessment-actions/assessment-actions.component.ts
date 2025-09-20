import { Component, Input, OnInit } from '@angular/core';
import { Angular2SmartTableModule, LocalDataSource, Settings } from 'angular2-smart-table';
import { Course, Assessment } from '../../home/course.model';
import { SharedDataService } from '../../services/shared-data.sevice';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AssessmentService } from '../../services/assessment.service';
import { NgIf } from '@angular/common';
import { AssessmentsComponent } from '../../assessments/assessments.component';

@Component({
  selector: 'app-assessment-actions',
  templateUrl: './assessment-actions.component.html',

  styleUrls: ['./assessment-actions.component.scss'],
  standalone: false
})
export class AssessmentActionsComponent implements OnInit {
  @Input() value!: string;
  course: Course | null = null;
  assessments: LocalDataSource = new LocalDataSource();

  settings: Settings = {
    hideSubHeader: true,
    actions: { add: false, edit: true, delete: true, position: 'right' },
    edit: {
      editButtonContent: "<i class='pi pi-pencil'></i>",
      confirmSave: true
    },
    delete: {
      deleteButtonContent: "<i class='pi pi-trash'></i>",
      confirmDelete: true
    },
    noDataMessage: 'No Assessments Available',
    attr: { class: "table table-bordered" },
    columns: {
      name: { title: 'Name' },
      category: { title: 'Category' },
      shortName: { title: 'Short Name' },
      maxPoints: { title: 'Weight (%)' }
    }
  };

  constructor(
    private sharedDataService: SharedDataService,
    private modalService: NgbModal,
    private assessmentService: AssessmentService
  ) {}

  ngOnInit() {
    this.course = this.sharedDataService.getSharedVariable();
    this.loadAssessments();
  }

  loadAssessments() {
    const allAssessments = this.course?.sections?.[0]?.assessments || [];

    const categoryMap: { [key: string]: string } = {
      QZ: 'Quiz',
      PE: 'Practical Examination',
      ME: 'Midterm',
      FE: 'Final'
    };

    const targetCategory = categoryMap[this.value];

    const filtered = allAssessments.filter(
      assessment => assessment.category === targetCategory
    );

    this.assessments.load(filtered);
  }

  close() {
    this.modalService.dismissAll();
  }

  openVerticallyCentered(content: any) {
    this.modalService.open(content, {
      backdrop: 'static',
      keyboard: false,
      centered: true
    });
  }

  updateAssessment(event: any) {
    const payload = event.newData;

    // 1. Validate that an individual assessment's weight does not exceed 40%
    if (payload.weight > 40) {
      alert("An individual assessment's weight cannot exceed 40%.");
      event.confirm.reject();
      return;
    }

    // 2. Compute the new total weight for the category based on a grouping of category and shortName.
    let categoryTotal = 0;
    const category = payload.category;
    const shortName = payload.shortName;
    // allowedTotal is the total weight allocated for this category as originally set.
    const allowedTotal = payload.totalWeight;

    if (this.course && this.course.sections && this.course.sections[0].assessments) {
      this.course.sections[0].assessments.forEach((assessment: any) => {
        if (assessment.category === category && assessment.shortName === shortName) {
          // For the assessment being updated, use the new value from payload.
          if (assessment.id === payload.id) {
            categoryTotal += payload.weight;
          } else {
            categoryTotal += assessment.weight;
          }
        }
      });
    }

    // 3. Check if the updated total exceeds the category's allotted weight.
    if (categoryTotal > allowedTotal) {
      alert(`Total weight for category ${category} would exceed the allowed total of ${allowedTotal}% by ${categoryTotal - allowedTotal}%. Please adjust the individual weights.`);
      event.confirm.reject();
      return;
    }

    // If all validations pass, update the assessment.
    this.assessmentService.updateAssessment(payload).subscribe({
      next: (data: Assessment) => event.confirm.resolve(event.newData),
      error: () => event.confirm.reject()
    });
  }

  deleteAssessment(event: any) {
    const assessmentId = event.data.id || event.data.assessmentId;
    if (!assessmentId) {
      console.error('Assessment ID is undefined:', event.data);
      event.confirm.reject();
      return;
    }
    if (confirm('Are you sure you want to delete this assessment?')) {
      this.assessmentService.deleteAssessment(assessmentId).subscribe({
        next: () => {
          event.confirm.resolve();
          if (this.course && this.course.sections && this.course.sections[0].assessments) {
            this.course.sections[0].assessments = this.course.sections[0].assessments.filter(
              (a: any) => a.id !== assessmentId && a.assessmentId !== assessmentId
            );
            this.sharedDataService.setSharedVariable(this.course);
          }
          AssessmentsComponent.updateList.next(true);
        },
        error: () => event.confirm.reject()
      });
    } else {
      event.confirm.reject();
    }
  }
}
