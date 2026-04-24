import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { SharedDataService } from '../services/shared-data.sevice';
import { PeerReviewService } from '../services/peer-review.service';
import { ReviewerAssignment, ReviewerSubmitPeerReviewRequest } from '../home/course.model';

@Component({
  selector: 'app-peer-review-reviewer',
  standalone: false,
  templateUrl: './peer-review-reviewer.component.html',
  styleUrls: ['./peer-review-reviewer.component.scss']
})
export class PeerReviewReviewerComponent implements OnInit {
  assignments: ReviewerAssignment[] = [];
  selectedAssignment: ReviewerAssignment | null = null;
  loading = false;
  submitting = false;
  message = '';
  isError = false;

  form: ReviewerSubmitPeerReviewRequest = {
    assignmentId: 0,
    reviewerId: 0,
    alignmentScore: null,
    alignmentComment: '',
    assessmentDesignScore: null,
    assessmentDesignComment: '',
    gradingClarityScore: null,
    gradingClarityComment: '',
    feedbackEfficiencyScore: null,
    feedbackEfficiencyComment: '',
    courseGradeDistributionNote: '',
    courseReflectionNote: '',
    innovationJourneyNote: '',
    otherNote: '',
    summary: ''
  };

  constructor(
    private peerReviewService: PeerReviewService,
    private sharedDataService: SharedDataService,
    private router: Router,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.loadAssignments();
  }

  loadAssignments(): void {
    const user = this.sharedDataService.currentUserValue;
    if (!user) {
      this.showMessage('Please sign in again.', true);
      return;
    }
    this.loading = true;
    this.peerReviewService.getReviewerAssignments(user.userId).subscribe({
      next: data => {
        this.assignments = data;
        this.loading = false;
      },
      error: () => {
        this.showMessage('Could not load peer review assignments.', true);
        this.loading = false;
      }
    });
  }

  startReview(assignment: ReviewerAssignment): void {
    const user = this.sharedDataService.currentUserValue;
    if (!user) return;
    this.selectedAssignment = assignment;
    this.form = {
      ...this.form,
      assignmentId: assignment.assignmentId,
      reviewerId: user.userId,
      alignmentScore: null,
      assessmentDesignScore: null,
      gradingClarityScore: null,
      feedbackEfficiencyScore: null,
      alignmentComment: '',
      assessmentDesignComment: '',
      gradingClarityComment: '',
      feedbackEfficiencyComment: '',
      courseGradeDistributionNote: '',
      courseReflectionNote: '',
      innovationJourneyNote: '',
      otherNote: '',
      summary: ''
    };
  }

  submitReview(): void {
    if (!this.selectedAssignment) return;
    this.submitting = true;
    this.peerReviewService.submitReview(this.form).subscribe({
      next: (response) => {
        this.submitting = false;
        this.showMessage(response);
        this.selectedAssignment = null;
        this.loadAssignments();
      },
      error: (err) => {
        this.submitting = false;
        this.showMessage(err?.error?.message || 'Submission failed.', true);
      }
    });
  }

  cancel(): void {
    this.selectedAssignment = null;
  }

  goBack(): void {
    if (window.history.length > 1) {
      this.location.back();
      return;
    }
    this.router.navigate(['/professor']);
  }

  private showMessage(message: string, isError = false): void {
    this.message = message;
    this.isError = isError;
    setTimeout(() => {
      this.message = '';
      this.isError = false;
    }, 4000);
  }
}
