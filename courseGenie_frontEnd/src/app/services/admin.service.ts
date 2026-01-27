import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Syllabus, SyllabusProgress} from '../home/course.model';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getSyllabusProgress() {
    return this.http.get<SyllabusProgress[]>(`${environment.apiUrl}/admin/syllabus-progress`);
  }
}
