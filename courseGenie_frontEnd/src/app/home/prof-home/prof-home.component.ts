import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject, Subscription } from 'rxjs';
import { map, switchMap, filter, withLatestFrom, tap, take } from 'rxjs/operators';
import { forkJoin } from 'rxjs';

import { CourseService } from '../../services/course.service';
import { SemesterService } from '../../services/semester.service';
import { SharedDataService } from '../../services/shared-data.sevice';
import { Course, CoursesBySemester, Section } from '../course.model';

import { CalendarService } from '../../services/calendar.service';
import { CalendarEvent } from '../course.model';
import { PeerReviewService } from '../../services/peer-review.service';

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
  templateUrl: './prof-home.component.html',
  styleUrls: ['./prof-home.component.scss']
})

export class ProfHomeComponent implements OnInit, OnDestroy {
  animationClass = '';
  semestersWithSections$!: Observable<SemesterViewModel[]>;
  private readonly sectionIdToNavigate$ = new Subject<number>();
  private navigationSubscription!: Subscription;
  todayEvents: CalendarEvent[] = [];
  isTodayLoading = false;
  allSemesters: string[] = [];
  selectedSemester: string = 'all';
  peerReviewPendingCount = 0;
  peerReviewCompletedCount = 0;
  peerReviewLoading = false;
  peerReviewVisible = false;

  constructor(
    private courseService: CourseService,
    private semesterService: SemesterService,
    private router: Router,
    private sharedDataService: SharedDataService,
    private calendarService: CalendarService,
    private peerReviewService: PeerReviewService,
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
    this.loadTodayEvents();
    this.loadSemesterOptions();
    this.loadPeerReviewStatus();
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

  goToCalendar(): void {
    this.router.navigate(['/professor/calendar']);
  }

  goToPeerReviews(): void {
    this.router.navigate(['/peer-reviews']);
  }
  private loadTodayEvents(): void {
    this.isTodayLoading = true;

    const todayIso = new Date().toISOString().split('T')[0];

    this.sharedDataService.currentUser$
      .pipe(
        filter(user => !!user),
        take(1),
        switchMap(user =>
          this.calendarService.getCalendar(user!.userId, todayIso, todayIso)
        )
      )
      .subscribe({
        next: (events) => {
          this.todayEvents = events;
          this.isTodayLoading = false;
        },
        error: () => {
          this.todayEvents = [];
          this.isTodayLoading = false;
        }
      });
  }

  private loadSemesterOptions(): void {
    this.semesterService.getAllSemesters().subscribe({
      next: (semesters) => {
        this.allSemesters = semesters;
      },
      error: () => {
        this.allSemesters = [];
      }
    });
  }

  private loadPeerReviewStatus(): void {
    const user = this.sharedDataService.currentUserValue;
    if (!user) return;
    this.peerReviewLoading = true;
    this.peerReviewService.getVisibility(user.userId).subscribe({
      next: visibility => {
        this.peerReviewVisible = visibility.visible && visibility.departmentAssigned;
        if (!this.peerReviewVisible) {
          this.peerReviewPendingCount = 0;
          this.peerReviewCompletedCount = 0;
          this.peerReviewLoading = false;
          return;
        }
        forkJoin({
          assignments: this.peerReviewService.getReviewerAssignments(user.userId),
          receivedReviews: this.peerReviewService.getReceivedReviews(user.userId)
        }).subscribe({
          next: ({ assignments, receivedReviews }) => {
            const pendingReviews = assignments.filter(a => !a.completed).length;
            const completedReviews = assignments.filter(a => a.completed).length;
            const pendingActionPlans = receivedReviews.filter(r => !r.reflectionSubmitted).length;
            const submittedActionPlans = receivedReviews.filter(r => r.reflectionSubmitted).length;

            // "Pending" reflects all remaining peer-review tasks.
            this.peerReviewPendingCount = pendingReviews + pendingActionPlans;
            this.peerReviewCompletedCount = completedReviews + submittedActionPlans;
            this.peerReviewLoading = false;
          },
          error: () => {
            this.peerReviewLoading = false;
            this.peerReviewCompletedCount = 0;
            this.peerReviewPendingCount = 0;
          }
        });
      },
      error: () => {
        this.peerReviewVisible = false;
        this.peerReviewLoading = false;
      }
    });
  }

  getFilteredSemesters(data: SemesterViewModel[]): SemesterViewModel[] {
    if (this.selectedSemester === 'all') {
      return data;
    }
    return data.filter(item => item.semester === this.selectedSemester);
  }
}
