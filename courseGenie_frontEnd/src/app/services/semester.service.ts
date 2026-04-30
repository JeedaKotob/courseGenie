import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SemesterService {
  apiUrl: string = `${environment.apiUrl}/semesters`;

  constructor(private http: HttpClient) {}

  getAllSemesters(): Observable<string[]> {
    return this.http.get<string[]>(this.apiUrl);
  }

  getCurrentSemesterName(): Observable<string> {
    return this.http.get(`${this.apiUrl}/current`, { responseType: 'text' });
  }
}
