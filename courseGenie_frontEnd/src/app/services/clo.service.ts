import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { CLO, Course, CoursesBySemester } from '../home/course.model';

@Injectable({
    providedIn: 'root'
})
export class CloService {
    apiUrl: string = `${environment.apiUrl}/clos`;
    constructor(private http: HttpClient) { }

    createCLO(payload: any) {
        return this.http.post<CLO>(`${this.apiUrl}`, payload);
    }

    updateClo(payload: any) {
        return this.http.put<CLO>(`${this.apiUrl}`, payload);
    }
}
