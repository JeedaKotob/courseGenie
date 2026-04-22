import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import {
  ExamCard,
  ExamRoom,
  ExamScheduleSaveRequest
} from '../home/course.model';
import { Router } from '@angular/router';
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
  private globalRemainingSeatsBySlotRoom = new Map<string, number>();

  constructor(
    private router: Router,
    private examRoomService: ExamRoomService,
    private location: Location,
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
    this.groupCardsBySlot();
    this.rebuildGlobalRemainingSeats();
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
        this.isSaving = false;
        this.toastr.success('Exam room assignments saved.');
        this.loadSchedulesForSelectedDate();
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
          startTime: schedule.startTime,
          endTime: schedule.endTime,
          roomIds: schedule.roomIds || [],
          roomSeatAvailability: schedule.roomSeatAvailability || [],
          enrolledStudentCount: schedule.enrolledStudentCount || 0,
          assignedSeatCapacity: schedule.assignedSeatCapacity || 0
        }));
        this.groupCardsBySlot();
        this.rebuildGlobalRemainingSeats();
        this.originalSnapshot = this.buildSnapshot();
        this.hasChanges = false;
      },
      error: () => this.toastr.error('Unable to load saved assignments for this date.')
    });
  }

  private groupCardsBySlot(): void {
    const grouped: Record<string, ExamCard[]> = {};

    this.examCards.forEach((card) => {
      const slot = this.getSlotLabel(card);
      if (!grouped[slot]) {
        grouped[slot] = [];
      }
      grouped[slot].push(card);
    });

    this.groupedCards = grouped;
  }

  private getSlotLabel(card: ExamCard): string {
    if (card.startTime && card.endTime) {
      return `${this.formatTime(card.startTime)} - ${this.formatTime(card.endTime)}`;
    }
    return 'Unscheduled';
  }

  private formatTime(value: string): string {
    return value?.slice(0, 5) || value;
  }

  private buildSnapshot(): string {
    return JSON.stringify(
      this.examCards.map((card) => ({
        examScheduleId: card.examScheduleId,
        roomIds: [...card.roomIds].sort((a, b) => a - b)
      }))
    );
  }

  getSelectedSeatCapacity(card: ExamCard): number {
    return this.getCardCapacityContext(card).effectiveSeats;
  }

  getUnseatedStudents(card: ExamCard): number {
    return this.getCardCapacityContext(card).unseated;
  }

  getRoomOptionLabel(card: ExamCard, room: ExamRoom): string {
    const slotKey = this.getSlotLabel(card);
    const seats = this.globalRemainingSeatsBySlotRoom.get(this.getSlotRoomKey(slotKey, room.roomId)) ?? room.capacity;
    return `${room.roomNumber} (${seats}/${room.capacity} seats left)`;
  }

  private rebuildGlobalRemainingSeats(): void {
    this.globalRemainingSeatsBySlotRoom = new Map<string, number>();
    const baseCapacityByRoom = new Map(this.rooms.map((room) => [room.roomId, room.capacity]));

    Object.entries(this.groupedCards).forEach(([slotKey, slotCards]) => {
      const remainingByRoom = new Map(baseCapacityByRoom);

      slotCards.forEach((slotCard) => {
        const selectedRoomIds = slotCard.roomIds || [];
        let toConsume = slotCard.enrolledStudentCount || 0;

        selectedRoomIds.forEach((roomId) => {
          if (toConsume <= 0) {
            return;
          }
          const remaining = remainingByRoom.get(roomId) || 0;
          const consumed = Math.min(remaining, toConsume);
          remainingByRoom.set(roomId, remaining - consumed);
          toConsume -= consumed;
        });
      });

      this.rooms.forEach((room) => {
        this.globalRemainingSeatsBySlotRoom.set(
          this.getSlotRoomKey(slotKey, room.roomId),
          remainingByRoom.get(room.roomId) || 0
        );
      });
    });
  }

  private getSlotRoomKey(slotKey: string, roomId: number): string {
    return `${slotKey}::${roomId}`;
  }

  private getCardCapacityContext(card: ExamCard): CardCapacityContext {
    return this.buildSlotCapacityContext().get(card.examScheduleId) || {
      effectiveSeats: 0,
      unseated: card.enrolledStudentCount,
      roomRemainingBefore: new Map<number, number>(),
      roomRemainingAfter: new Map<number, number>()
    };
  }

  private buildSlotCapacityContext(): Map<number, CardCapacityContext> {
    const context = new Map<number, CardCapacityContext>();
    const baseCapacityByRoom = new Map(this.rooms.map((room) => [room.roomId, room.capacity]));

    Object.values(this.groupedCards).forEach((slotCards) => {
      const remainingByRoom = new Map(baseCapacityByRoom);

      slotCards.forEach((slotCard) => {
        const selectedRoomIds = slotCard.roomIds || [];
        const roomRemainingBefore = new Map<number, number>(remainingByRoom);

        const effectiveSeats = selectedRoomIds.reduce(
          (total, roomId) => total + (remainingByRoom.get(roomId) || 0),
          0
        );

        const requiredSeats = slotCard.enrolledStudentCount || 0;
        let toConsume = Math.min(requiredSeats, effectiveSeats);

        selectedRoomIds.forEach((roomId) => {
          if (toConsume <= 0) {
            return;
          }
          const remaining = remainingByRoom.get(roomId) || 0;
          const consumed = Math.min(remaining, toConsume);
          remainingByRoom.set(roomId, remaining - consumed);
          toConsume -= consumed;
        });

        context.set(slotCard.examScheduleId, {
          effectiveSeats,
          unseated: Math.max(0, requiredSeats - effectiveSeats),
          roomRemainingBefore,
          roomRemainingAfter: new Map<number, number>(remainingByRoom)
        });
      });
    });

    return context;
  }

  private getTodayDate(): string {
    return new Date().toISOString().split('T')[0];
  }

  goBack(): void {
    if (window.history.length > 1) {
      this.location.back();
      return;
    }
    this.router.navigate(['/admin']);
  }
}

interface CardCapacityContext {
  effectiveSeats: number;
  unseated: number;
  roomRemainingBefore: Map<number, number>;
  roomRemainingAfter: Map<number, number>;
}
