import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from '../services/course.service';
import { Course } from '../home/course.model';
import {map, distinctUntilChanged, switchMap} from 'rxjs/operators';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-overview',
  standalone: false,
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {
  course$!: Observable<Course>;
  termLabel$!: Observable<string>;
  cloNumber$!: Observable<number>;

  constructor(
    private route: ActivatedRoute,
    private courseService: CourseService
  ) { }

  ngOnInit() {
    this.course$ = this.route.paramMap.pipe(
      map( pm => ({
        courseCode: pm.get('courseCode')!,
        sectionCode: pm.get('sectionCode')!
      })),
      distinctUntilChanged((a,b) => a.courseCode === b.courseCode && a.sectionCode === b.sectionCode),
      switchMap(({ courseCode, sectionCode}) =>
        this.courseService.getCourseByCourseCodeAndSectionCode(courseCode,sectionCode)
      )
    )

    this.termLabel$=this.course$.pipe(
      map(c=>c?.sections?.[0]?.term ?? 'No term available')
    )

    this.cloNumber$=this.course$.pipe(
      map(course => course.sections
        .flatMap(section => section.assessments)
        .reduce((acc, assessment)=>acc+(assessment?.clos?.length ?? 0),0)
      )
    )
  }

}
