import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Course, Professor } from '../home/course.model';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {
  
  public static currentUser: Professor = JSON.parse(localStorage.getItem("currentUser") || "{}");

  // Use a BehaviorSubject to store and emit the shared variable
  private sharedVariable = new BehaviorSubject<Course | null>(null);
  
  // Observable for other components to subscribe to
  sharedVariable$ = this.sharedVariable.asObservable();

  // Method to set the shared variable
  setSharedVariable(value: Course | null): void {
    this.sharedVariable.next(value);   
  }

  // Method to get the current value
  getSharedVariable(): Course | null {
    return this.sharedVariable.getValue();
  }
}
