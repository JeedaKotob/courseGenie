import { Injectable } from '@angular/core';
import { User } from '../home/course.model';
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

  private normalizeRole(r: string): string {
    return String(r).toUpperCase().replace(/^ROLE_/, '');
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
  // hasRole(role: string): boolean {
  //   const user = this.currentUserValue;
  //   if (!user || !user.roles) return false;
  //
  //   // Make sure the role has the ROLE_ prefix
  //   if (!role.startsWith('ROLE_')) {
  //     role = 'ROLE_' + role;
  //   }
  //
  //   return user.roles.includes(role);
  // }
  hasRole(role: string): boolean {
    const need = this.normalizeRole(role);

    const user = this.currentUserValue;
    let have: string[] = Array.isArray(user?.roles)
    ? user.roles.map((r: string)=> this.normalizeRole(r))
      : [];
    if (have.length === 0) {
      const payload = this.readTokenPayload();
      const raw = (payload?.roles ?? payload?.authorities ?? []) as string[];
      have = raw.map(r => this.normalizeRole(r));
    }
    return have.includes(need);
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
    return this.http.post<User>(`${this.apiUrl}/authenticate`, payload)
      .pipe(
        tap(response => {
          localStorage.setItem('currentUser', JSON.stringify(response));
          localStorage.setItem('token', JSON.stringify(response.jwtToken));
          this.currentUserSubject.next(response);
        })
      );
  }
}
