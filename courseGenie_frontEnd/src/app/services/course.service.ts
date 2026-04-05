import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Course, CoursesBySemester, Section } from '../home/course.model';

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

  getSemesterNames(): Observable<string[]> {
    return this.http.get<string[]>(`${environment.apiUrl}/sections/terms`);
  }
  
  getProfessorsByDepartment(departmentName: string): Observable<any[]> {
    return this.http.get<any[]>(`${environment.apiUrl}/users/professors`, {
      params: { departmentName }
    });
  }

  createSection(
    courseCode: string,
    payload: { code: string; semesterName: string; professorId: number }
  ): Observable<Section> {
    return this.http.post<Section>(`${this.apiUrl}/${encodeURIComponent(courseCode)}/sections`, payload);
  }
  
  updateSection(
    courseCode: string,
    sectionId: number,
    payload: { code: string; semesterName: string; professorId: number }
  ): Observable<Section> {
    return this.http.put<Section>(
      `${this.apiUrl}/${encodeURIComponent(courseCode)}/sections/${sectionId}`,
      payload
    );
  }
  
  deleteSection(courseCode: string, sectionId: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/${encodeURIComponent(courseCode)}/sections/${sectionId}`
    );
  }
}
