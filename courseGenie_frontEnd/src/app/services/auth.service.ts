import { Injectable } from '@angular/core';
import { Professor } from '../home/course.model';
import { environment } from '../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  apiUrl: string = `${environment.apiUrl}/auth`;
  private currentUserSubject: BehaviorSubject<any>;
  public currentUser: Observable<any>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<any>(this.getCurrentUserFromStorage());
    this.currentUser = this.currentUserSubject.asObservable();
  }

  private getCurrentUserFromStorage(): any {
    const userStr = localStorage.getItem('currentUser');
    return userStr ? JSON.parse(userStr) : null;
  }

  public get currentUserValue(): any {
    return this.currentUserSubject.value;
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !!token; // Returns true if the token exists, otherwise false
  }

  // Check if user has a specific role
  hasRole(role: string): boolean {
    const user = this.currentUserValue;
    if (!user || !user.roles) return false;

    // Make sure the role has the ROLE_ prefix
    if (!role.startsWith('ROLE_')) {
      role = 'ROLE_' + role;
    }

    return user.roles.includes(role);
  }

  // Check if user has any of the specified roles
  hasAnyRole(roles: string[]): boolean {
    return roles.some(role => this.hasRole(role));
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

  doLogin(payload: any) {
    return this.http.post<Professor>(`${this.apiUrl}/authenticate`, payload)
      .pipe(
        tap(response => {
          localStorage.setItem('currentUser', JSON.stringify(response));
          localStorage.setItem('token', JSON.stringify(response.jwtToken));
          this.currentUserSubject.next(response);
        })
      );
  }
}
