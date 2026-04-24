import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { User, UserProfileUpdateRequest } from '../home/course.model';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {
  private readonly apiUrl = `${environment.apiUrl}/users/me`;

  constructor(private http: HttpClient) {}

  getCurrentUserProfile() {
    return this.http.get<User>(this.apiUrl);
  }

  updateCurrentUserProfile(payload: UserProfileUpdateRequest) {
    return this.http.put<User>(this.apiUrl, payload);
  }
}