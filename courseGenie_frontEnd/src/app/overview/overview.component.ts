import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from '../services/course.service';
import { Course } from '../home/course.model';
import { SharedDataService } from '../services/shared-data.sevice';

@Component({
  selector: 'app-overview',
  standalone: false,
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {
  course: Course | null = null;
  termLabel = 'No term available';
  loading: boolean = false; // <-- Added loading state

  constructor(
    private sharedDataService: SharedDataService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    // Check query params to see if we are in loading mode
    this.route.queryParams.subscribe(params => {
      this.loading = params['loading'] === 'true';
    });

    // Always try to fetch the shared course data
    this.course = this.sharedDataService.getSharedVariable();

    if (this.course?.sections?.length) {
      this.termLabel = this.course.sections[0].term;
    }
  }

  get cloNumber() {
    if (!this.course) return 0;
    return this.course.sections
      .flatMap(section => section.assessments)
      .reduce((acc, assessment) => acc + assessment.clos.length, 0);
  }



}
