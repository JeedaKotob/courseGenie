import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Professor, Course } from '../home/course.model';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {
  private currentUserSubject = new BehaviorSubject<Professor | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  private selectedCourseSubject = new BehaviorSubject<Course | null>(null);
  public selectedCourse$ = this.selectedCourseSubject.asObservable();

  constructor() {
    const storedUser = localStorage.getItem('currentUser');
    if (storedUser) {
      this.currentUserSubject.next(JSON.parse(storedUser));
    }
  }

  setCurrentUser(user: Professor): void {
    localStorage.setItem('currentUser', JSON.stringify(user));
    this.currentUserSubject.next(user);
  }

  logout(): void {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

  public get currentUserValue(): Professor | null {
    return this.currentUserSubject.getValue();
  }

  setSelectedCourse(course: Course | null): void {
    this.selectedCourseSubject.next(course);
  }

  public get selectedCourseValue(): Course | null {
    return this.selectedCourseSubject.getValue();
  }
}
