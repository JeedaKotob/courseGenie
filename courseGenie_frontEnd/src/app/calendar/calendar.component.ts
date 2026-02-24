import { Component, OnInit } from '@angular/core';
import { filter, switchMap, take } from 'rxjs/operators';
import { CalendarService } from '../services/calendar.service';
import { SharedDataService } from '../services/shared-data.sevice';
import { CalendarEvent } from '../home/course.model';
import { CalendarDay } from '../home/course.model';

@Component({
  selector: 'app-calendar',
  standalone: false,
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.scss'],
})
export class CalendarComponent implements OnInit {

  currentYear!: number;
  currentMonth!: number;

  weeks: CalendarDay[][] = [];

  isLoading = false;
  errorMessage: string | null = null;

  readonly weekdayLabels = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];

  constructor(
    private calendarService: CalendarService,
    private sharedDataService: SharedDataService
  ) {}

  ngOnInit(): void {
    const today = new Date();
    console.log('SYSTEM DATE:', today);
    this.currentYear = today.getFullYear();
    this.currentMonth = today.getMonth();
    this.loadCalendar();
  }

  get monthLabel(): string {
    const date = new Date(this.currentYear, this.currentMonth, 1);
    return date.toLocaleDateString(undefined, {
      month: 'long',
      year: 'numeric',
    });
  }

  changeMonth(offset: number): void {
    const newMonth = this.currentMonth + offset;
    const date = new Date(this.currentYear, newMonth, 1);
    this.currentYear = date.getFullYear();
    this.currentMonth = date.getMonth();
    this.loadCalendar();
  }

  goToToday(): void {
    const today = new Date();
    this.currentYear = today.getFullYear();
    this.currentMonth = today.getMonth();
    this.loadCalendar();
  }

  private loadCalendar(): void {
    this.isLoading = true;
    this.errorMessage = null;

    const { startIso, endIso } = this.getGridRangeIso(
      this.currentYear,
      this.currentMonth
    );

    this.sharedDataService.currentUser$
      .pipe(
        filter((user) => !!user),
        take(1),
        switchMap((user) =>
          this.calendarService.getCalendar(user!.userId, startIso, endIso)
        )
      )
      .subscribe({
        next: (events) => {
          this.buildCalendarGrid(events);
          this.isLoading = false;
        },
        error: () => {
          this.errorMessage = 'Unable to load calendar. Please try again.';
          this.isLoading = false;
        },
      });
  }

  private getGridRangeIso(year: number, month: number): {
    startIso: string;
    endIso: string;
  } {
    const firstOfMonth = new Date(year, month, 1);
    const lastOfMonth = new Date(year, month + 1, 0);

    const firstDayOffset = (firstOfMonth.getDay() + 6) % 7;
    const gridStart = new Date(firstOfMonth);
    gridStart.setDate(firstOfMonth.getDate() - firstDayOffset);

    const lastDayOffset = (lastOfMonth.getDay() + 6) % 7;
    const gridEnd = new Date(lastOfMonth);
    gridEnd.setDate(lastOfMonth.getDate() + (6 - lastDayOffset));

    return {
      startIso: gridStart.toISOString().split('T')[0],
      endIso: gridEnd.toISOString().split('T')[0],
    };
  }
  private buildCalendarGrid(events: CalendarEvent[]): void {
    const eventsByDate = new Map<string, CalendarEvent[]>();
    events.forEach((event) => {
      const key = event.date; // assuming YYYY-MM-DD
      if (!eventsByDate.has(key)) eventsByDate.set(key, []);
      eventsByDate.get(key)!.push(event);
    });

    const firstOfMonth = new Date(this.currentYear, this.currentMonth, 1);
    const lastOfMonth = new Date(this.currentYear, this.currentMonth + 1, 0);

    const startDayOffset = (firstOfMonth.getDay() + 6) % 7;
    const currentDate = new Date(firstOfMonth);
    currentDate.setDate(firstOfMonth.getDate() - startDayOffset);

    const newWeeks: CalendarDay[][] = [];
    const today = new Date();

    while (currentDate <= lastOfMonth) {
      const week: CalendarDay[] = [];

      for (let i = 0; i < 7; i++) {
        const date = new Date(currentDate);
        const isoDate = date.toISOString().split('T')[0];

        const inCurrentMonth =
          date.getMonth() === this.currentMonth &&
          date.getFullYear() === this.currentYear;

        const isToday =
          date.getDate() === today.getDate() &&
          date.getMonth() === today.getMonth() &&
          date.getFullYear() === today.getFullYear();

        const dayEvents = eventsByDate.get(isoDate) ?? [];

        week.push({
          date,
          isoDate,
          inCurrentMonth,
          isToday,
          events: dayEvents,
        });

        currentDate.setDate(currentDate.getDate() + 1);
      }

      newWeeks.push(week);
    }

    this.weeks = newWeeks;
  }
}
