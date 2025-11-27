import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject, Subscription } from 'rxjs';
import { map, switchMap, filter, withLatestFrom, tap } from 'rxjs/operators';

import { CourseService } from '../services/course.service';
import { SharedDataService } from '../services/shared-data.sevice';
import { Course, CoursesBySemester, Section } from './course.model';

type SemesterViewModel = {
  semester: string;
  sections: {
    sectionId: number;
    courseName: string;
    courseCode: string;
    sectionCode: string;
    credits: string;
  }[];
};

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent implements OnInit, OnDestroy {
  animationClass = '';
  semestersWithSections$!: Observable<SemesterViewModel[]>;
  private readonly sectionIdToNavigate$ = new Subject<number>();
  private navigationSubscription!: Subscription;

  constructor(
    private courseService: CourseService,
    private router: Router,
    private sharedDataService: SharedDataService
  ) {}

  ngOnInit(): void {
    const coursesBySemester$ = this.sharedDataService.currentUser$.pipe(
      filter(user => !!user),
      switchMap(user => this.courseService.getCoursesByProfessorId(user!.userId))
    );

    this.semestersWithSections$ = coursesBySemester$.pipe(
      map(coursesBySemester => {
        if (!coursesBySemester) {
          return [];
        }
        return Object.keys(coursesBySemester).map(semester => {
          const sections = coursesBySemester[semester].flatMap(course =>
            course.sections.map(section => ({
              sectionId: section.sectionId,
              courseName: course.name,
              courseCode: course.code,
              sectionCode: section.code,
              credits: course.credits
            }))
          );
          return { semester, sections };
        });
      })
    );

    this.navigationSubscription = this.sectionIdToNavigate$.pipe(
      withLatestFrom(coursesBySemester$),
      tap(([sectionId, coursesBySemester]) => {
        const { course, section } = this.findCourseAndSectionById(sectionId, coursesBySemester);
        if (course && section) {
          this.router.navigate(['/overview', course.code, section.code]);
        }
      })
    ).subscribe();

    setTimeout(() => { this.animationClass = 'animate-hero'; }, 100);
  }

  navigateToOverview(sectionId: number): void {
    this.sectionIdToNavigate$.next(sectionId);
  }

  private findCourseAndSectionById(sectionId: number, courses: CoursesBySemester): { course?: Course, section?: Section } {
    for (const semester in courses) {
      for (const course of courses[semester]) {
        const section = course.sections.find(s => s.sectionId === sectionId);
        if (section) {
          return { course, section };
        }
      }
    }
    return {};
  }

  ngOnDestroy(): void {
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
  }
}
