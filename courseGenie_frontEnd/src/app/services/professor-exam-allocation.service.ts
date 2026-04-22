import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import {
  ProfessorExamAllocation,
  SaveProfessorExamAllocationRequest
} from '../home/course.model';

@Injectable({
  providedIn: 'root'
})
export class ProfessorExamAllocationService {
  private apiUrl = `${environment.apiUrl}/professor-exam-allocations`;

  constructor(private http: HttpClient) {}

  getBySection(sectionId: number, professorId: number): Observable<ProfessorExamAllocation> {
    const params = new HttpParams().set('professorId', professorId);
    return this.http.get<ProfessorExamAllocation>(`${this.apiUrl}/${sectionId}`, { params });
  }

  saveBySection(
    sectionId: number,
    professorId: number,
    payload: SaveProfessorExamAllocationRequest
  ): Observable<ProfessorExamAllocation> {
    const params = new HttpParams().set('professorId', professorId);
    return this.http.put<ProfessorExamAllocation>(`${this.apiUrl}/${sectionId}`, payload, { params });
  }

  notifyStudents(sectionId: number, professorId: number): Observable<string> {
    const params = new HttpParams().set('professorId', professorId);
    return this.http.post(`${this.apiUrl}/${sectionId}/notify-students`, {}, {
      params,
      responseType: 'text'
    });
  }
}
