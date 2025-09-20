import { Component, OnInit } from '@angular/core';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem
} from '@angular/cdk/drag-drop';
import { AssessmentService } from '../services/assessment.service';
import { Assessment, Course } from '../home/course.model';
import { SharedDataService } from '../services/shared-data.sevice';
import { forkJoin, Observable } from 'rxjs';

@Component({
  selector: 'app-course-calendar',
  templateUrl: './course-calendar.component.html',
  styleUrls: ['./course-calendar.component.scss'],
  standalone: false
})
export class CourseCalendarComponent implements OnInit {
  course: Course | null = null;
  public weeks: Array<{ week: number, assessments: Assessment[], isActive: boolean }> = [];
  public assessments: Assessment[] = [];
  public showSuccess = false;
  private startDate = new Date('2025-01-15');

  constructor(
    private assessmentService: AssessmentService,
    private sharedService: SharedDataService
  ) {}

  ngOnInit(): void {
    this.course = this.sharedService.getSharedVariable();
    this.initializeWeeks();
    this.loadAssessments();
  }

  private initializeWeeks(): void {
    const today = new Date();
    const timeDiff = today.getTime() - this.startDate.getTime();
    const currentWeek = Math.floor(timeDiff / (7 * 24 * 60 * 60 * 1000)) + 1;

    for (let i = 1; i <= 14; i++) {
      this.weeks.push({
        week: i,
        assessments: [],
        isActive: i === currentWeek
      });
    }
  }

  private loadAssessments(): void {
    this.assessmentService.getallassesments().subscribe({
      next: (data: Assessment[]) => {
        this.weeks.forEach(week => week.assessments = []);
        let unscheduledIndex = 0;
        data.forEach((assessment) => {
          if (assessment.week && assessment.week !== 0) {
            const weekContainer = this.weeks.find(w => w.week === assessment.week);
            if (weekContainer) weekContainer.assessments.push(assessment);
          } else {
            const weekContainer = this.weeks[unscheduledIndex];
            weekContainer.assessments.push(assessment);
            unscheduledIndex = (unscheduledIndex + 1) % this.weeks.length;
          }
        });
      },
      error: (err) => console.error('Error loading assessments:', err)
    });
  }

  public getWeekClass(week: { week: number, isActive: boolean }): string {
    return week.isActive ? 'active-week' : 'pastel-week';
  }

  public dropWeek(event: CdkDragDrop<Assessment[]>): void {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      const movedAssessment = event.previousContainer.data[event.previousIndex];
      movedAssessment.week = event.container.data[0]?.week || 0;
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
  }

  public dropUnscheduled(event: CdkDragDrop<Assessment[]>): void {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      const movedAssessment = event.previousContainer.data[event.previousIndex];
      movedAssessment.week = 0;
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
  }

  headerButtons = [
    {
      label: 'Save Calendar',
      icon: 'save',
      action: 'save-calendar',
      class: 'primary-btn'
    }
  ];

  handleHeaderAction(action: string): void {
    if (action === 'save-calendar') {
      this.saveCalendar();
    }
  }

  getWeekDate(weekNumber: number): string {
    const weekStart = new Date(this.startDate);
    weekStart.setDate(weekStart.getDate() + (weekNumber - 1) * 7);
    const weekEnd = new Date(weekStart);
    weekEnd.setDate(weekEnd.getDate() + 6);

    const formatDate = (date: Date) => {
      const month = date.toLocaleString('default', { month: 'short' });
      const day = date.getDate();
      return `${month} ${day}`;
    };

    return `${formatDate(weekStart)} - ${formatDate(weekEnd)}`;
  }

  public saveCalendar(): void {
    const updateObservables: Observable<any>[] = [];

    this.weeks.forEach(weekContainer => {
      weekContainer.assessments.forEach(assessment => {
        if (assessment.week !== weekContainer.week) {
          assessment.week = weekContainer.week;
        }
        updateObservables.push(
          this.assessmentService.updateAssessmentWeek(assessment.assessmentId, weekContainer.week)
        );
      });
    });

    if (updateObservables.length > 0) {
      forkJoin(updateObservables).subscribe({
        next: () => {
          this.showSuccess = true;
          setTimeout(() => {
            this.showSuccess = false;
          }, 5000);
        },
        error: (error) => {
          console.error("Error saving calendar:", error);
        }
      });
    }
  }
}
