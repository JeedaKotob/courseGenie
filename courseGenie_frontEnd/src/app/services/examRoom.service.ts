import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { ExamRoom } from '../home/course.model';
import {Observable} from 'rxjs';
import { ExamSchedule, ExamScheduleSaveRequest } from '../home/course.model';

@Injectable({
  providedIn: 'root'
})
export class ExamRoomService {
  apiUrl: string = `${environment.apiUrl}/examRooms`;
  scheduleApiUrl: string = `${environment.apiUrl}/examSchedules`;

  constructor(private http: HttpClient) { }

  getAllRooms(): Observable<ExamRoom[]> {
    return this.http.get<ExamRoom[]>(this.apiUrl);
  }

  getSchedulesByDate(examDate: string): Observable<ExamSchedule[]> {
    return this.http.get<ExamSchedule[]>(`${this.scheduleApiUrl}?examDate=${examDate}`);
  }

  saveSchedulesByDate(payload: ExamScheduleSaveRequest): Observable<ExamSchedule[]> {
    return this.http.put<ExamSchedule[]>(this.scheduleApiUrl, payload);
  }

}
