import { Injectable } from '@angular/core';
import { User } from '../home/course.model';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  apiUrl: string = `${environment.apiUrl}/auth`;
  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser: Observable<User | null>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User | null>(this.getCurrentUserFromStorage());
    this.currentUser = this.currentUserSubject.asObservable();
  }

  private getTokenRaw(): string | null {
    const raw = localStorage.getItem('token') || localStorage.getItem('access_token');
    if (!raw) return null;
    try { return JSON.parse(raw); } catch { return raw; }
  }

  private readTokenPayload(): any | null {
    const tok = this.getTokenRaw();
    if (!tok) return null;
    try { return JSON.parse(atob(tok.split('.')[1])); } catch { return null; }
  }

  private getCurrentUserFromStorage(): User | null {
    const userStr = localStorage.getItem('currentUser');
    return userStr ? JSON.parse(userStr) : null;
  }

  public get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  // ---- FIXED ROLE CHECKING ----
  hasRole(role: string): boolean {
    const user = this.currentUserValue;

    if (!user) return false;

    const roles: string[] =
      user.roles ??
      this.readTokenPayload()?.roles ??
      this.readTokenPayload()?.authorities ??
      [];

    return roles.includes(role); // exact match (ROLE_PROFESSOR)
  }

  hasAnyRole(roles: string[]): boolean {
    return roles.some(r => this.hasRole(r));
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

  doLogin(payload: any) {
    return this.http.post<User>(`${this.apiUrl}/authenticate`, payload)
      .pipe(
        tap(response => {
          localStorage.setItem('currentUser', JSON.stringify(response));
          localStorage.setItem('token', response.jwtToken);
          this.currentUserSubject.next(response);
        })
      );
  }
}
