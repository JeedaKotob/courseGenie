import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { CLO, Course, CoursesBySemester, Grade, Student } from '../home/course.model';

@Injectable({
    providedIn: 'root'
})
export class GradeService {
    apiUrl: string = `${environment.apiUrl}/grades`;
    constructor(private http: HttpClient) { }

    saveGrades(grades: Grade[]) {
        return this.http.post<Boolean>(`${this.apiUrl}`, grades);
    }

    getGradesBySection(sectionId: number) {
      return this.http.get<Grade[]>(
        `${this.apiUrl}/section/${sectionId}`
      );
    }

}
