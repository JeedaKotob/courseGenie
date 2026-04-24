import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import {
  PeerReviewProfessorVisibility,
  RevieweeReceivedReview,
  ReviewerAssignment,
  ReviewerSubmitPeerReviewRequest
} from '../home/course.model';

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

  getVisibility(userId: number): Observable<PeerReviewProfessorVisibility> {
    return this.http.get<PeerReviewProfessorVisibility>(`${environment.apiUrl}/peer-review/reviewer/visibility`, {
      params: { userId }
    });
  }

  submitReview(payload: ReviewerSubmitPeerReviewRequest): Observable<string> {
    return this.http.post(`${environment.apiUrl}/peer-review/reviewer/submit`, payload, {
      responseType: 'text'
    });
  }

  getReceivedReviews(revieweeId: number): Observable<RevieweeReceivedReview[]> {
    return this.http.get<RevieweeReceivedReview[]>(`${environment.apiUrl}/peer-review/reviewer/received`, {
      params: { revieweeId }
    });
  }

  submitReflection(peerReviewId: number, revieweeId: number, actionPlan: string): Observable<string> {
    return this.http.post(`${environment.apiUrl}/peer-review/reviewer/reflection`, {
      peerReviewId,
      revieweeId,
      actionPlan
    }, { responseType: 'text' });
  }
}
