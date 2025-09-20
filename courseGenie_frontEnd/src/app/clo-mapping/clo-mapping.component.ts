import { Component, OnInit } from '@angular/core';
import { Course } from '../home/course.model';
import { SharedDataService } from '../services/shared-data.sevice';
import { AssessmentService } from '../services/assessment.service';
import { SectionService } from '../services/section.service';
import { NgForOf, NgIf } from '@angular/common';
import { forkJoin, Observable } from 'rxjs';
import { ButtonComponent } from '../shared/button/button.component';

@Component({
  selector: 'app-clo-mapping',
  templateUrl: './clo-mapping.component.html',
  standalone: false,
  styleUrls: ['./clo-mapping.component.scss']
})
export class CloMappingComponent implements OnInit {
  course: Course | null = null;
  selectedMappings: { [assessmentId: number]: number[] } = {};

  showSuccessMessage: boolean = false;
  showAssessmentError: boolean = false;
  showCloError: boolean = false;

  constructor(
    private sharedDataService: SharedDataService,
    private assessmentService: AssessmentService,
    private sectionService: SectionService
  ) {}

  ngOnInit() {
    this.course = this.sharedDataService.getSharedVariable();

    this.course?.sections?.[0]?.assessments?.forEach(assessment => {
      this.selectedMappings[assessment.assessmentId] = assessment.clos?.map(clo => clo.cloId) || [];
    });
  }

  onCheckboxChange(event: Event, assessmentId: number, cloId: number) {
    const input = event.target as HTMLInputElement;
    const isChecked = input.checked;

    const cloList = this.selectedMappings[assessmentId] || [];

    if (isChecked) {
      if (!cloList.includes(cloId)) {
        cloList.push(cloId);
      }
    } else {
      const index = cloList.indexOf(cloId);
      if (index > -1) {
        cloList.splice(index, 1);
      }
    }

    this.selectedMappings[assessmentId] = cloList;
  }

  isChecked(assessmentId: number, cloId: number): boolean {
    return this.selectedMappings[assessmentId]?.includes(cloId) ?? false;
  }

  saveConfiguration() {
    let isValid = true;
    this.showAssessmentError = false;
    this.showCloError = false;
    this.showSuccessMessage = false;

    // Validate each assessment has at least one CLO
    const hasAssessmentError = (this.course?.sections?.[0]?.assessments || []).some(
      assessment => !this.selectedMappings[assessment.assessmentId] || this.selectedMappings[assessment.assessmentId].length === 0
    );

    if (hasAssessmentError) {
      this.showAssessmentError = true;
      isValid = false;
    }

    // Validate each CLO is mapped to at least 2 assessments
    const cloToAssessmentsMap: { [cloId: number]: number[] } = {};
    for (const clo of this.course?.clos || []) {
      cloToAssessmentsMap[clo.cloId] = [];
    }

    for (const assessmentId in this.selectedMappings) {
      for (const cloId of this.selectedMappings[+assessmentId]) {
        cloToAssessmentsMap[cloId].push(+assessmentId);
      }
    }

    const hasCloError = (this.course?.clos || []).some(
      clo => (cloToAssessmentsMap[clo.cloId]?.length || 0) < 2
    );

    if (hasCloError) {
      this.showCloError = true;
      isValid = false;
    }

    if (!isValid) return;

    const mappingObservables: Observable<boolean>[] = [];

    for (const assessment of this.course?.sections?.[0]?.assessments || []) {
      const currentMappedCLOIds = assessment.clos ? assessment.clos.map(clo => clo.cloId) : [];
      const newMappedCLOIds = this.selectedMappings[assessment.assessmentId] || [];

      const cloIdsToAdd = newMappedCLOIds.filter(cloId => !currentMappedCLOIds.includes(cloId));
      const cloIdsToRemove = currentMappedCLOIds.filter(cloId => !newMappedCLOIds.includes(cloId));

      cloIdsToAdd.forEach(cloId => {
        mappingObservables.push(this.assessmentService.mapCloToAssessment(assessment.assessmentId, cloId));
      });

      cloIdsToRemove.forEach(cloId => {
        mappingObservables.push(this.assessmentService.unmapCloToAssessment(assessment.assessmentId, cloId));
      });
    }

    forkJoin(mappingObservables).subscribe({
      next: () => {
        this.course?.sections?.[0]?.assessments?.forEach(assessment => {
          const mappedCLOIds = this.selectedMappings[assessment.assessmentId];
          assessment.clos = this.course?.clos?.filter(clo => mappedCLOIds.includes(clo.cloId)) || [];
        });

        this.course!.sections![0].configured = true;
        this.sharedDataService.setSharedVariable(this.course);

        this.showSuccessMessage = true;
        this.showAssessmentError = false;
        this.showCloError = false;

        console.log('CLO mappings saved successfully');
      },
      error: (error) => {
        console.error('Error saving CLO mappings', error);
      }
    });
  }

  closeSuccessMessage() {
    this.showSuccessMessage = false;
  }

  closeAssessmentError() {
    this.showAssessmentError = false;
  }

  closeCloError() {
    this.showCloError = false;
  }
  headerButtons = [
    {
      label: 'Save', // Or appropriate label for the component
      icon: 'save',  // Or appropriate icon
      action: 'save-action', // Or appropriate action
      class: 'primary-btn'
    }
    // Add more buttons as needed
  ];

// Handle header button clicks
  handleHeaderAction(action: string): void {
    if (action === 'save-action') {
      this.saveConfiguration(); // Call the save method for this component
      // Call appropriate action method for the component
      // e.g., this.saveGrades() or this.saveConfiguration(), etc.
    }
    // Handle other actions as needed
  }
}
