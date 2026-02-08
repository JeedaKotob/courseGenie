import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Syllabus } from '../home/course.model';

@Injectable({
    providedIn: 'root'
})
export class SyllabusService {
    apiUrl: string = `${environment.apiUrl}/syllabuses`;

    constructor(private http: HttpClient) { }


    generateSyllabus(id: number | undefined) {
        return this.http.post<Syllabus>(`${this.apiUrl}/generate/${id}`, null);
    }

    submitSyllabus(id: number) {
        return this.http.post<void>(`${this.apiUrl}/submit/${id}`, null);
    }

    getAssessmentTotal(id: number) {
      return this.http.get<number>(`${this.apiUrl}/${id}/assessment-total`);
    }



}
