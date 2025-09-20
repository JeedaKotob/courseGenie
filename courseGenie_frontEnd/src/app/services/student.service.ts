import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { CLO, Course, CoursesBySemester, Grade, Student } from '../home/course.model';

@Injectable({
    providedIn: 'root'
})
export class StudentService {
    apiUrl: string = `${environment.apiUrl}/students`;
    constructor(private http: HttpClient) { }

    getAllStudents(sectionId: number |undefined) {
        return this.http.get<Map<Student, Grade[]>>(`${this.apiUrl}/${sectionId}`);
    }
}
