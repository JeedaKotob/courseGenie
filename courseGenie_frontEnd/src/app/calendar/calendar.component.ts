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

  showAddEventModal = false;

  newEvent = {
    title: '',
    date: '',
    startTime: '',
    endTime: '',
    room: ''
  };
  timeOptions: string[] = [];

  constructor(
    private calendarService: CalendarService,
    private sharedDataService: SharedDataService
  ) {}

  ngOnInit(): void {
    const today = new Date();
    this.currentYear = today.getFullYear();
    this.currentMonth = today.getMonth();
    this.generateTimeOptions();
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
      startIso: this.formatDate(gridStart),
      endIso: this.formatDate(gridEnd),
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
        const isoDate = date.getFullYear() + '-' +
          String(date.getMonth() + 1).padStart(2, '0') + '-' +
          String(date.getDate()).padStart(2, '0');

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

  saveEvent(): void {

    if (!this.newEvent.date ||
      !this.newEvent.startTime ||
      !this.newEvent.endTime ||
      !this.newEvent.title) {

      alert('Please fill all required fields.');
      return;
    }

    this.sharedDataService.currentUser$
      .pipe(
        filter(user => !!user),
        take(1),
        switchMap(user =>
          this.calendarService.addEvent({
            userId: user!.userId,
            eventName: this.newEvent.title,
            eventDate: this.newEvent.date,
            startTime: this.newEvent.startTime,
            endTime: this.newEvent.endTime,
            room: this.newEvent.room
          })
        )
      )
      .subscribe({
        next: () => {
          this.closeAddEventModal();
          this.loadCalendar();
        },
        error: () => {
          alert('Failed to add event.');
        }
      });
  }

  openAddEventModal(): void {
    this.showAddEventModal = true;
    this.newEvent.date = this.formatDate(new Date());
  }

  closeAddEventModal(): void {
    this.showAddEventModal = false;

    this.newEvent = {
        title: '',
        date: '',
        startTime: '',
        endTime: '',
        room: ''
      };
  }
  private formatDate(date: Date): string {
    return date.getFullYear() + '-' +
      String(date.getMonth() + 1).padStart(2, '0') + '-' +
      String(date.getDate()).padStart(2, '0');
  }

  private generateTimeOptions(): void {
    for (let hour = 0; hour < 24; hour++) {
      this.timeOptions.push(`${String(hour).padStart(2, '0')}:00`);
      this.timeOptions.push(`${String(hour).padStart(2, '0')}:30`);
    }
  }

  onStartTimeChange(): void {
    if (!this.newEvent.startTime) return;

    const [hour, minute] = this.newEvent.startTime.split(':').map(Number);

    let endHour = hour + 1;
    if (endHour >= 24) endHour = 23;

    this.newEvent.endTime =
      `${String(endHour).padStart(2, '0')}:${String(minute).padStart(2, '0')}`;
  }
}
