import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Course, CoursesBySemester } from '../home/course.model';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  apiUrl: string = `${environment.apiUrl}/courses`;
  constructor(private http: HttpClient) {}

  // Fetch all courses
  getCourses(): Observable<CoursesBySemester> {
    return this.http.get<CoursesBySemester>(this.apiUrl);
  }
  // Fetch all courses as a flat list of CourseDTOs
  getAllCourseDTOs(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.apiUrl}/course`);
  }

  // Fetch a single course by ID
  getCourseByCourseCodeAndSectionCode(courseCode: string, sectionCode: string): Observable<Course> {
    return this.http.get<Course>(`${this.apiUrl}/${courseCode}/section/${sectionCode}`);
  }

  // Add a new course
  addCourse(course: Course): Observable<Course> {
    return this.http.post<Course>(this.apiUrl, course);
  }

  // Update an existing course
  updateCourse(course: Course): Observable<Course> {
    return this.http.put<Course>(`${this.apiUrl}/${course.courseId}`, course);
  }

  // Delete a course by ID
  deleteCourse(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  // Delete a course by ID
  getCoursesByProfessorId(professorId: number): Observable<CoursesBySemester> {
    return this.http.get<CoursesBySemester>(`${this.apiUrl}/${professorId}`);
  }

  getCourseByCode(courseCode: string): Observable<Course> {
    const code = encodeURIComponent(courseCode.trim());
    return this.http.get<Course>(`${this.apiUrl}/code/${code}`);
  }



}
