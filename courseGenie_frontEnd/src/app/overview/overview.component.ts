import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from '../services/course.service';
import { Course } from '../home/course.model';
import { SharedDataService } from '../services/shared-data.sevice';
import {map, distinctUntilChanged, switchMap} from 'rxjs/operators';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-overview',
  standalone: false,
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {
  // course: Course | null = null;
  // termLabel = 'No term available';
  // loading: boolean = false; // <-- Added loading state

  // we change vars to observables;
  course$!: Observable<Course>;
  termLabel$!: Observable<string>;
  cloNumber$!: Observable<number>;

  constructor(
    // private sharedDataService: SharedDataService,
    private route: ActivatedRoute,
    private courseService: CourseService
  ) { }

  ngOnInit() {
    // // Check query params to see if we are in loading mode
    // this.route.queryParams.subscribe(params => {
    //   this.loading = params['loading'] === 'true';
    // });
    //
    // // Always try to fetch the shared course data
    // this.course = this.sharedDataService.getSharedVariable();
    //
    // if (this.course?.sections?.length) {
    //   this.termLabel = this.course.sections[0].term;
    // }
    this.course$ = this.route.paramMap.pipe(
      map( pm => ({
        courseCode: pm.get('courseCode')!,
        sectionCode: pm.get('sectionCode')!
      })),
      distinctUntilChanged((a,b) => a.courseCode === b.courseCode && a.sectionCode === b.sectionCode),
      // get course
      switchMap(({ courseCode, sectionCode}) =>
        this.courseService.getCourseByCourseCodeAndSectionCode(courseCode,sectionCode)
      )
    )

    this.termLabel$=this.course$.pipe(
      map(c=>c?.sections?.[0].term ?? 'No term available')
    )

    this.cloNumber$=this.course$.pipe(
      map(course => course.sections
        .flatMap(section => section.assessments)
        .reduce((acc, assessment)=>acc+(assessment?.clos?.length ?? 0),0)
      )
    )
  }

  // get cloNumber() {
  //   if (!this.course) return 0;
  //   return this.course.sections
  //     .flatMap(section => section.assessments)
  //     .reduce((acc, assessment) => acc + assessment.clos.length, 0);
  // }

}
