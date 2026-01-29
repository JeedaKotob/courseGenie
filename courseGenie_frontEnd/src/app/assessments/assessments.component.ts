import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { LocalDataSource, Settings } from 'angular2-smart-table';
import { Course } from '../home/course.model';
import { SharedDataService } from '../services/shared-data.sevice';
import { AssessmentService } from '../services/assessment.service';
import { AssessmentActionsComponent } from '../datatable-actions/assessment-actions/assessment-actions.component';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { CreateAssessmentComponent } from '../modals/create-assessment/create-assessment.component';

@Component({
  selector: 'app-assessments',
  standalone: false,
  templateUrl: './assessments.component.html',
  styleUrls: ['./assessments.component.scss']
})

export class AssessmentsComponent implements OnInit, OnDestroy { // <-- FIX #2: ADD OnDestroy HERE
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

  categoryDescriptionsSource: LocalDataSource = new LocalDataSource();
  tableData: LocalDataSource = new LocalDataSource();
  categoryDescriptions: { category: string, description: string, actionName: string }[] = [];
  course: Course | null = null;

  private destroy$ = new Subject<void>();

  constructor(
    private sharedDataService: SharedDataService,
    private assessmentService: AssessmentService
  ) {}

  ngOnInit() {
    this.sharedDataService.selectedCourse$
      .pipe(takeUntil(this.destroy$))
      .subscribe(currentCourse => {
        this.course = currentCourse;

        if (this.course) {
          this.initializeComponentData();
        } else {
          this.tableData.load([]);
          this.categoryDescriptionsSource.load([]);
        }
      });
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }

  initializeComponentData() {
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

  handleHeaderAction(action: string): void {
    if (action === 'save-descriptions') {
      this.onSaveCategoryDescription({
        newData: this.categoryDescriptions
      });
    } else if (action === 'create-assessment') {
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
      const key = assessment.category;
      if (!assessmentCount[key]) {
        assessmentCount[key] = {
          category: assessment.category,
          shortName: assessment.shortName.replace(/[0-9]+$/, ''),
          number: 0,
          actionName: assessment.shortName.replace(/[0-9]+$/, '')
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

    this.categoryDescriptions = Array.from(uniqueCategories).map(category => ({
      category,
      description: '',
      actionName: category
    }));
    this.categoryDescriptionsSource.load(this.categoryDescriptions);
  }

  onSaveCategoryDescription(event: { newData: Array<{ category: string; description: string; }> }) {
    const updatedList = event.newData;
    const sectionId = this.course?.sections?.[0]?.sectionId;

    if (!sectionId) {
      console.warn('No section ID available; aborting save.');
      return;
    }

    updatedList.forEach(cd => {
      this.assessmentService.createCategoryDescription(sectionId, cd.category, cd.description)
        .subscribe({
          next: () => {},
          error: err => {
            if (err.status === 409) {
              this.assessmentService.updateCategoryDescription(sectionId, cd.category, cd.description)
                .subscribe({
                  next: () => {},
                  error: updateErr => console.error(`Failed to update "${cd.category}"`, updateErr)
                });
            } else {
              console.error(`Failed to create "${cd.category}"`, err);
            }
          }
        });
    });

    this.categoryDescriptionsSource.load(this.categoryDescriptions);
  }

  existingAssessmentTotal(): number {
    return this.course?.sections?.[0]?.assessments
      ?.reduce((total, assessment) => total + (assessment.maxPoints || 0), 0) || 0;
  }

}
