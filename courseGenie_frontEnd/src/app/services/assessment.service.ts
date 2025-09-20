import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Assessment, Course, CoursesBySemester } from '../home/course.model';

@Injectable({
  providedIn: 'root'
})
export class AssessmentService {
  apiUrl: string = `${environment.apiUrl}/assessments`;

  constructor(private http: HttpClient) {}

  createAssessment(payload: any): Observable<Assessment> {
    return this.http.post<Assessment>(`${this.apiUrl}`, payload);
  }

  updateAssessment(payload: any): Observable<Assessment> {
    return this.http.put<Assessment>(`${this.apiUrl}`, payload);
  }

  saveCloMappings(courseId: number | undefined, mappings: { [assessmentId: number]: number[] }) {
    return this.http.post(`/api/save-clo-mappings`, {
      courseId,
      mappings
    });
  }

  mapCloToAssessment(assessmentId: number, cloId: number): Observable<boolean> {
    return this.http.post<boolean>(`${this.apiUrl}/${assessmentId}/clo/${cloId}`, null);
  }

  unmapCloToAssessment(assessmentId: number, cloId: number): Observable<boolean> {
    return this.http.put<boolean>(`${this.apiUrl}/${assessmentId}/clo/${cloId}`, null);
  }

  deleteAssessment(assessmentID: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${assessmentID}`);
  }

  getallassesments(): Observable<Assessment[]> {
    return this.http.get<Assessment[]>(`${this.apiUrl}`);
  }

  updateAssessmentWeek(assessmentId: number, week: number): Observable<Assessment> {
    return this.http.put<Assessment>(`${this.apiUrl}/${assessmentId}/week`, { week });
  }

  createCategoryDescription(sectionId: number, categoryName: string, description: string): Observable<void> {
    return this.http.post<void>(
      `${this.apiUrl}/${sectionId}/category-description/${categoryName}`,
      null,
      { params: { description } }
    );
  }

  getCategoryDescription(sectionId: number, categoryName: string): Observable<string> {
    return this.http.get(
      `${this.apiUrl}/${sectionId}/category-description/${categoryName}`,
      { responseType: 'text' }
    );
  }

  updateCategoryDescription(sectionId: number, categoryName: string, description: string): Observable<void> {
    return this.http.put<void>(
      `${this.apiUrl}/${sectionId}/category-description/${categoryName}`,
      null,
      { params: { description } }
    );
  }

  deleteCategoryDescription(sectionId: number, categoryName: string): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/${sectionId}/category-description/${categoryName}`
    );
  }
}
