import { Component, OnInit } from '@angular/core';
import {
  ExamCard,
  ExamRoom,
  ExamSchedule,
  ExamScheduleSaveRequest
} from '../home/course.model';
import { ExamRoomService } from '../services/examRoom.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-exam-room',
  standalone: false,
  templateUrl: './exam-room.component.html',
  styleUrl: './exam-room.component.scss'
})
export class ExamRoomComponent implements OnInit {
  rooms: ExamRoom[] = [];
  examCards: ExamCard[] = [];
  groupedCards: Record<string, ExamCard[]> = {};
  selectedDate: string = this.getTodayDate();
  hasChanges = false;
  isSaving = false;

  private originalSnapshot = '';

  constructor(
    private examRoomService: ExamRoomService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.loadPageData();
  }

  private loadPageData(): void {
    this.examRoomService.getAllRooms().subscribe({
      next: (roomData) => {
        this.rooms = roomData;
        this.loadSchedulesForSelectedDate();
      },
      error: () => this.toastr.error('Unable to load rooms.')
    });
  }

  onDateChange(): void {
    this.loadSchedulesForSelectedDate();
  }

  onCardChanged(): void {
    this.groupCardsByTimeSlot();
    this.hasChanges = this.buildSnapshot() !== this.originalSnapshot;
  }

  saveAssignments(): void {
    if (!this.selectedDate || !this.hasChanges) {
      return;
    }

    this.isSaving = true;
    const payload: ExamScheduleSaveRequest = {
      examDate: this.selectedDate,
      assignments: this.examCards.map((card) => ({
        examScheduleId: card.examScheduleId,
        roomIds: card.roomIds
      }))
    };

    this.examRoomService.saveSchedulesByDate(payload).subscribe({
      next: () => {
        this.originalSnapshot = this.buildSnapshot();
        this.hasChanges = false;
        this.isSaving = false;
        this.toastr.success('Exam room assignments saved.');
      },
      error: (error) => {
        this.isSaving = false;
        const message = error?.error?.message || 'Unable to save exam room assignments.';
        this.toastr.error(message);
      }
    });
  }

  private loadSchedulesForSelectedDate(): void {
    if (!this.selectedDate) {
      return;
    }

    this.examRoomService.getSchedulesByDate(this.selectedDate).subscribe({
      next: (schedules) => {
        this.examCards = schedules.map((schedule) => ({
          examScheduleId: schedule.examScheduleId,
          courseId: schedule.courseId,
          courseCode: schedule.courseCode,
          courseName: schedule.courseName,
          semesterName: schedule.semesterName,
          timeSlot: schedule.timeSlot,
          roomIds: schedule.roomIds || []
        }));
        this.groupCardsByTimeSlot();
        this.originalSnapshot = this.buildSnapshot();
        this.hasChanges = false;
      },
      error: () => this.toastr.error('Unable to load saved assignments for this date.')
    });
  }

  private groupCardsByTimeSlot(): void {
    const grouped: Record<string, ExamCard[]> = {};

    this.examCards.forEach((card) => {
      const slot = card.timeSlot || 'Unassigned';
      if (!grouped[slot]) {
        grouped[slot] = [];
      }
      grouped[slot].push(card);
    });

    this.groupedCards = grouped;
  }

  private buildSnapshot(): string {
    return JSON.stringify(
      this.examCards.map((card) => ({
        examScheduleId: card.examScheduleId,
        roomIds: [...card.roomIds].sort((a, b) => a - b)
      }))
    );
  }

  private getTodayDate(): string {
    return new Date().toISOString().split('T')[0];
  }
}
