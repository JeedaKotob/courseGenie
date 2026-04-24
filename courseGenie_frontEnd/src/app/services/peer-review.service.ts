import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { ReviewerAssignment, ReviewerSubmitPeerReviewRequest } from '../home/course.model';

@Injectable({
  providedIn: 'root'
})
export class PeerReviewService {
  constructor(private http: HttpClient) {}

  getReviewerAssignments(reviewerId: number): Observable<ReviewerAssignment[]> {
    return this.http.get<ReviewerAssignment[]>(`${environment.apiUrl}/peer-review/reviewer/assignments`, {
      params: { reviewerId }
    });
  }

  submitReview(payload: ReviewerSubmitPeerReviewRequest): Observable<string> {
    return this.http.post(`${environment.apiUrl}/peer-review/reviewer/submit`, payload, {
      responseType: 'text'
    });
  }
}
