import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from '../services/course.service';
import { Course, CourseCollaborator } from '../home/course.model';
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
  collaborators$!: Observable<CourseCollaborator[]>;
  semesterLabel$!: Observable<string>;
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

    this.semesterLabel$=this.course$.pipe(
      map(c=>c?.sections?.[0]?.semesterName ?? 'No semester available')
    )

    this.collaborators$ = this.route.paramMap.pipe(
      map(pm => ({
        courseCode: pm.get('courseCode')!,
        sectionCode: pm.get('sectionCode')!
      })),
      distinctUntilChanged((a, b) => a.courseCode === b.courseCode && a.sectionCode === b.sectionCode),
      switchMap(({ courseCode, sectionCode }) =>
        this.courseService.getCourseCollaborators(courseCode, sectionCode)
      )
    );

    this.cloNumber$=this.course$.pipe(
      map(course => course.sections
        .flatMap(section => section.assessments)
        .reduce((acc, assessment)=>acc+(assessment?.clos?.length ?? 0),0)
      )
    )
  }

  buildProfessorGmailLink(email: string, courseCode: string, sectionCode: string): string {
    const subject = encodeURIComponent(`Collaboration from ${courseCode}-${sectionCode}`);
    return `https://mail.google.com/mail/?view=cm&fs=1&to=${encodeURIComponent(email)}&su=${subject}`;
  }

  buildGroupGmailLink(collaborators: CourseCollaborator[], courseCode: string, sectionCode: string): string {
    const emails = collaborators
      .map(c => c.professorEmail)
      .filter(email => !!email)
      .join(',');
    const subject = encodeURIComponent(`Cross-course collaboration from ${courseCode}-${sectionCode}`);
    return `https://mail.google.com/mail/?view=cm&fs=1&to=${encodeURIComponent(emails)}&su=${subject}`;
  }
}
