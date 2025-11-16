import { Component, OnInit } from '@angular/core';
import { LocalDataSource, Settings } from 'angular2-smart-table';
import { CLO, Course } from '../home/course.model';
import { SharedDataService } from '../services/shared-data.sevice';
import { CloService } from '../services/clo.service';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-clo-listing',
  standalone: false,
  templateUrl: './clo-listing.component.html',
  styleUrl: './clo-listing.component.scss'
})
export class CloListingComponent implements OnInit {
  data = [
    { name: 'Project Alpha', description: 'Initial phase of the development.', id: 101 },
    { name: 'Beta Launch', description: 'Testing phase with limited users.', id: 102 },
    { name: 'Marketing Campaign', description: 'Social media and ads promotion.', id: 103 },
    { name: 'User Feedback', description: 'Collecting feedback from early adopters.', id: 104 },
    { name: 'Bug Fix Sprint', description: 'Resolving critical issues.', id: 105 },
    { name: 'UI Revamp', description: 'Improving the user interface.', id: 106 },
    { name: 'Final Deployment', description: 'Releasing to production.', id: 107 }
  ];

  settings: Settings = {
    hideSubHeader: true,
    actions: {
      add: false,
      edit: false, // Changed from true to false to disable inline editing
      delete: false,
      position: 'right',
    },
    /*
    // Temporarily removed Actions column
    edit: {
      editButtonContent: "<i class='pi pi-pencil'></i>",
      confirmSave: true
    },
    */
    noDataMessage: 'Aucune donnée trouvée',
    attr: {
      class: "table table-bordered"
    },
    columns: {
      name: {
        title: 'Name',
        width: '10%'
      },
      description: {
        title: 'Description',
        width: '80%'
      }
      /*
      // Temporarily commented out Actions column
      , actionName: {
        title: 'Actions',
        type: 'custom',
        renderComponent: CloActionsComponent,
        width: '10%'
      }
      */
    }
  };

  tableData: LocalDataSource = new LocalDataSource();
  course: Course | null = null;
  public static updateList: Subject<boolean> = new Subject();

  constructor(private sharedDataService: SharedDataService, private cloService: CloService) {
    CloListingComponent.updateList.subscribe(res => {
      this.course = this.sharedDataService.selectedCourseValue;
      this.getCLOSData();
    });
  }

  ngOnInit() {
    this.course = this.sharedDataService.selectedCourseValue;
    this.getCLOSData();
  }

  getCLOSData() {
    this.tableData.load(this.course?.clos.sort((a, b) => a.cloId - b.cloId) ?? []);
  }

  updateClo(event: any) {
    let payload = event.newData;
    this.cloService.updateClo(payload).subscribe({
      next: (data: CLO) => {
        event.confirm.resolve(event.newData);
        const elementIndex = this.course?.clos?.findIndex((clo: any) => clo.cloId === data.cloId);
        if (elementIndex !== undefined && elementIndex !== -1 && this.course?.clos) {
          this.course.clos[elementIndex].description = data.description;
          this.course.clos[elementIndex].name = data.name;
          this.sharedDataService.setSelectedCourse(this.course);
        }
      },
      error: () => {
        event.confirm.reject();
      }
    });
  }
}
