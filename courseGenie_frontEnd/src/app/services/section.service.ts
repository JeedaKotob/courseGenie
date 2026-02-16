import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

import {Course, CoursesBySemester, Enrollment, Student} from '../home/course.model';

@Injectable({
    providedIn: 'root'
})
export class SectionService {
    apiUrl: string = `${environment.apiUrl}/sections`;

    constructor(private http: HttpClient) { }

    saveConfiguration(id: number | undefined) {
        return this.http.put<Boolean>(`${this.apiUrl}/${id}`, null);
    }

  getTeachingMethodology(sectionId: number): Observable<string> {
    return this.http.get(
      `${this.apiUrl}/${sectionId}/teaching-methodology`,
      { responseType: 'text' }
    );
  }

    setTeachingMethodology(sectionId: number, methodologyText: string): Observable<void> {
        return this.http.post<void>(`${this.apiUrl}/${sectionId}/teaching-methodology`, methodologyText, {
            headers: { 'Content-Type': 'text/plain' }
        });
    }

  getEnrollmentsBySection(sectionId: number): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(`${this.apiUrl}/${sectionId}/enrollments`);
  }

  getSectionById(sectionId: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${sectionId}`);
  }

}
