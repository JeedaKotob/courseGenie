import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { ExamRoom } from '../home/course.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExamRoomService {
  apiUrl: string = `${environment.apiUrl}/examRooms`;

  constructor(private http: HttpClient) { }

  getAllRooms(): Observable<ExamRoom[]> {
    return this.http.get<ExamRoom[]>(this.apiUrl);
  }

}
