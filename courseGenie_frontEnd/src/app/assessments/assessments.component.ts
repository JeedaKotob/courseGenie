import { Component, OnInit, ViewChild } from '@angular/core';
import { LocalDataSource, Settings } from 'angular2-smart-table';
import { Course } from '../home/course.model';
import { SharedDataService } from '../services/shared-data.sevice';
import { AssessmentService } from '../services/assessment.service';
import { AssessmentActionsComponent } from '../datatable-actions/assessment-actions/assessment-actions.component';
import { Subject } from 'rxjs';
import { CreateAssessmentComponent } from '../modals/create-assessment/create-assessment.component';

@Component({
  selector: 'app-assessments',
  standalone: false,
  templateUrl: './assessments.component.html',
  styleUrls: ['./assessments.component.scss']
})
export class AssessmentsComponent implements OnInit {
  @ViewChild(CreateAssessmentComponent) createAssessmentComponent!: CreateAssessmentComponent;

  settings: Settings = {
    hideSubHeader: true,
    actions: false,
    noDataMessage: 'No Assessments Available',
    attr: {
      class: "table table-bordered"
    },
    columns: {
      category: {title: 'Category'},
      shortName: {title: 'Short Name'},
      number: {title: 'Number Of Assessments'},
      actionName: {
        title: 'Actions',
        type: 'custom',
        renderComponent: AssessmentActionsComponent,
        componentInitFunction: (instance: AssessmentActionsComponent, row: any) => {
          instance.value = row.value;
        },
        width: '10%'
      }
    }
  };

  categoryDescriptionsSource: LocalDataSource = new LocalDataSource();
  source: LocalDataSource = new LocalDataSource();

  categoryDescriptions: { category: string, description: string, actionName: string }[] = [];

  categoryDescriptionSettings: Settings = {
    hideSubHeader: true,
    actions: false,
    noDataMessage: 'No Category Descriptions Available',
    attr: {
      class: "table table-bordered mt-4"
    },
    columns: {
      category: {title: 'Category'},
      description: {title: 'Description'},
      actionName: {
        title: 'Actions',
        type: 'custom',
        renderComponent: AssessmentActionsComponent,
        componentInitFunction: (instance: AssessmentActionsComponent, row: any) => {
          instance.value = row.value;
        },
        width: '10%'
      }
    }
  };

  tableData: LocalDataSource = new LocalDataSource();
  course: Course | null = null;
  public static updateList: Subject<boolean> = new Subject();

  constructor(
    private sharedDataService: SharedDataService,
    private assessmentService: AssessmentService
  ) {
    AssessmentsComponent.updateList.subscribe(res => {
      this.course = this.sharedDataService.getSharedVariable();
      this.prepareTableData();
      this.prepareCategoryDescriptions();
    });
  }

  ngOnInit() {
    this.course = this.sharedDataService.getSharedVariable();
    this.prepareTableData();

    const sectionId = this.course?.sections?.[0]?.sectionId;
    if (sectionId) {
      this.prepareCategoryDescriptions();
      const categories = this.categoryDescriptions;

      const requests = categories.map((item) =>
        this.assessmentService.getCategoryDescription(sectionId, item.category)
      );

      Promise.all(requests.map(req => req.toPromise())).then(responses => {
        this.categoryDescriptions = responses.map((desc, i) => ({
          category: categories[i].category,
          description: desc ?? '',
          actionName: categories[i].category
        }));

        this.categoryDescriptionsSource.load(this.categoryDescriptions);
      }).catch(err => {
        console.error("Error fetching category descriptions", err);
        this.categoryDescriptions = categories;
        this.categoryDescriptionsSource.load(this.categoryDescriptions);
      });
    }
  }

  headerButtons = [
    {
      label: 'Create Assessment',
      icon: 'plus-circle',
      action: 'create-assessment',
      class: 'primary-btn'
    },
    {
      label: 'Save Descriptions',
      icon: 'save',
      action: 'save-descriptions',
      class: 'secondary-btn'
    }
  ];

  // Handle header button clicks
  handleHeaderAction(action: string): void {
    if (action === 'save-descriptions') {
      this.onSaveCategoryDescription({
        newData: this.categoryDescriptions
      });
    } else if (action === 'create-assessment') {
      // Now we actually call the createAssessmentComponent to open the modal
      if (this.createAssessmentComponent) {
        this.createAssessmentComponent.openVerticallyCentered(this.createAssessmentComponent.addAssessmentModal);
      }
    }
  }

  prepareTableData() {
    const assessmentCount: Record<string, {
      category: string;
      shortName: string;
      number: number;
      actionName: string
    }> = {};

    this.course?.sections?.[0].assessments?.forEach((assessment: any) => {
      const key = assessment.category; // ✅ Only group by category

      if (!assessmentCount[key]) {
        assessmentCount[key] = {
          category: assessment.category,
          shortName: assessment.shortName.replace(/[0-9]+$/, ''), // ✅ Strip numbers from short name like QZ1 -> QZ
          number: 0,
          actionName: assessment.shortName.replace(/[0-9]+$/, '') // Or just use shortName without numbers
        };
      }

      assessmentCount[key].number++;
    });

    this.tableData.load(Object.values(assessmentCount));
  }

  prepareCategoryDescriptions(): void {
    const uniqueCategories = new Set<string>();
    this.course?.sections?.[0].assessments?.forEach((assessment: any) => {
      if (assessment.category) {
        uniqueCategories.add(assessment.category);
      }
    });

    // Build the categoryDescriptions array and load it into the table
    this.categoryDescriptions = Array.from(uniqueCategories).map(category => ({
      category,
      description: '',
      actionName: category
    }));
    this.categoryDescriptionsSource.load(this.categoryDescriptions);
  }

  /**
   * Bulk–save all category descriptions at once.
   */
  onSaveCategoryDescription(event: { newData: Array<{ category: string; description: string; }> }) {
    const updatedList = event.newData;
    const sectionId = this.course?.sections?.[0]?.sectionId;
    console.log('Saving category descriptions:', {sectionId, updatedList});

    if (!sectionId) {
      console.warn('No section ID available; aborting save.');
      return;
    }

    // For each category, try to create it; if it already exists (409), then update it
    updatedList.forEach(cd => {
      this.assessmentService.createCategoryDescription(sectionId, cd.category, cd.description)
        .subscribe({
          next: () => { /* created */
          },
          error: err => {
            if (err.status === 409) {
              this.assessmentService.updateCategoryDescription(sectionId, cd.category, cd.description)
                .subscribe({
                  next: () => { /* updated */
                  },
                  error: updateErr => console.error(`Failed to update "${cd.category}"`, updateErr)
                });
            } else {
              console.error(`Failed to create "${cd.category}"`, err);
            }
          }
        });
    });

    // Reload the table so the user sees the saved values
    this.categoryDescriptionsSource.load(this.categoryDescriptions);
  }
}
