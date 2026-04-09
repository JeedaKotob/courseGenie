import { Component } from '@angular/core';
import { Location } from '@angular/common';
import {ExamRoom} from '../home/course.model';
import {Router} from '@angular/router';
import {ExamRoomService} from '../services/examRoom.service';


@Component({
  selector: 'app-exam-room',
  standalone: false,
  templateUrl: './exam-room.component.html',
  styleUrl: './exam-room.component.scss'
})
export class ExamRoomComponent {
  rooms: ExamRoom[] = [];

  constructor(
    private router: Router,
    private examRoomService: ExamRoomService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.loadRooms();
  }

  loadRooms(): void {
    this.examRoomService.getAllRooms().subscribe({
      next: (data) => {
        this.rooms = data;
        console.log('Rooms loaded:', data);
      },
      error: (err) => {
        console.error('Error loading rooms', err);
      }
    });
  }

  goBack(): void {
    if (window.history.length > 1) {
      this.location.back();
      return;
    }
    this.router.navigate(['/admin']);
  }
}
