import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {CarProgress, SyllabusProgress} from '../home/course.model';
import { environment } from '../../environments/environment';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getSyllabusProgress(): Observable<{ [key: string]: SyllabusProgress[] }> {
    return this.http.get<{ [key: string]: SyllabusProgress[] }>(`${environment.apiUrl}/admin/syllabus-progress`);
  }

  sendReminders(): Observable<string> {
    return this.http.post(`${environment.apiUrl}/admin/send-reminders`, {},
      { responseType: 'text' }
    );
  }

  getCarProgress(): Observable<{ [key: string]: CarProgress[] }> {
    return this.http.get<{ [key: string]: CarProgress[] }>(`${environment.apiUrl}/admin/car-progress`);
  }

  sendCarReminders(): Observable<string> {
    return this.http.post(`${environment.apiUrl}/admin/send-car-reminders`, {},
      { responseType: 'text' }
    );
  }

}
