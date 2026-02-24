import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { CalendarEvent } from '../home/course.model';

@Injectable({
  providedIn: 'root'
})
export class CalendarService {

  private apiUrl: string = `${environment.apiUrl}/calendar`;

  constructor(private http: HttpClient) {}

  getCalendar(
    userId: number,
    start: string,
    end: string
  ): Observable<CalendarEvent[]> {

    const params = new HttpParams()
      .set('userId', userId.toString())
      .set('start', start)
      .set('end', end);

    return this.http.get<CalendarEvent[]>(this.apiUrl, { params });
  }
}
