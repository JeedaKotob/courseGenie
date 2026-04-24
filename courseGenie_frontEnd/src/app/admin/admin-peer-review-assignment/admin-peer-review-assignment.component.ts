import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import {
  PeerReviewAssignment,
  PeerReviewDepartmentOverview,
  PeerReviewPairRequest,
  ProfessorOption
} from '../../home/course.model';

@Component({
  selector: 'app-admin-peer-review-assignment',
  standalone: false,
  templateUrl: './admin-peer-review-assignment.component.html',
  styleUrls: ['./admin-peer-review-assignment.component.scss']
})
export class AdminPeerReviewAssignmentComponent implements OnInit {
  departments: PeerReviewDepartmentOverview[] = [];
  selectedDepartment = '';
  selectedReviewerId: number | null = null;
  selectedRevieweeId: number | null = null;
  reviewsPerProfessor = 1;
  assignments: PeerReviewAssignment[] = [];

  loading = false;
  autoPairLoading = false;
  saveLoading = false;
  message = '';
  isError = false;

  constructor(
    private adminService: AdminService,
    private router: Router,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.loadDepartments();
  }

  get selectedDepartmentProfessors(): ProfessorOption[] {
    return this.departments.find(dept => dept.departmentName === this.selectedDepartment)?.professors ?? [];
  }

  loadDepartments(): void {
    this.loading = true;
    this.adminService.getPeerReviewDepartments().subscribe({
      next: (data) => {
        this.departments = data;
        if (!this.selectedDepartment && data.length > 0) {
          this.selectedDepartment = data[0].departmentName;
          this.loadAssignments();
        }
        this.loading = false;
      },
      error: () => {
        this.showMessage('Could not load departments.', true);
        this.loading = false;
      }
    });
  }

  onDepartmentChange(): void {
    this.selectedReviewerId = null;
    this.selectedRevieweeId = null;
    this.loadAssignments();
  }

  loadAssignments(): void {
    if (!this.selectedDepartment) return;
    this.adminService.getPeerReviewAssignments(this.selectedDepartment).subscribe({
      next: (data) => {
        this.assignments = data;
      },
      error: () => this.showMessage('Could not load existing assignments.', true)
    });
  }

  autoPair(): void {
    if (!this.selectedDepartment) return;
    this.autoPairLoading = true;
    this.adminService.autoPairPeerReviews(this.selectedDepartment, this.reviewsPerProfessor).subscribe({
      next: (data) => {
        this.assignments = data;
        this.autoPairLoading = false;
        this.showMessage('Auto pairing completed.');
      },
      error: (err) => {
        const msg = err?.error?.message || 'Auto pairing failed.';
        this.showMessage(msg, true);
        this.autoPairLoading = false;
      }
    });
  }

  addManualAssignment(): void {
    if (!this.selectedReviewerId || !this.selectedRevieweeId) {
      this.showMessage('Select both reviewer and reviewee first.', true);
      return;
    }
    if (this.selectedReviewerId === this.selectedRevieweeId) {
      this.showMessage('Self-review is not allowed.', true);
      return;
    }
    const duplicate = this.assignments.some(a =>
      a.reviewerId === this.selectedReviewerId && a.revieweeId === this.selectedRevieweeId
    );
    if (duplicate) {
      this.showMessage('That directional pair already exists.', true);
      return;
    }

    const reviewer = this.selectedDepartmentProfessors.find(p => p.userId === this.selectedReviewerId);
    const reviewee = this.selectedDepartmentProfessors.find(p => p.userId === this.selectedRevieweeId);
    if (!reviewer || !reviewee) {
      this.showMessage('Reviewer or reviewee is invalid for this department.', true);
      return;
    }

    this.assignments = [
      ...this.assignments,
      {
        assignmentId: 0,
        reviewerId: reviewer.userId,
        reviewerName: reviewer.fullName,
        revieweeId: reviewee.userId,
        revieweeName: reviewee.fullName,
        departmentName: this.selectedDepartment,
        pairingSource: 'MANUAL'
      }
    ];
    this.selectedReviewerId = null;
    this.selectedRevieweeId = null;
    this.showMessage('Pair added locally. Click Save Assignments to persist.');
  }

  removeAssignment(index: number): void {
    this.assignments = this.assignments.filter((_, i) => i !== index);
  }

  saveAssignments(): void {
    if (!this.selectedDepartment) return;
    this.saveLoading = true;
    const payload: PeerReviewPairRequest[] = this.assignments.map(a => ({
      reviewerId: a.reviewerId,
      revieweeId: a.revieweeId
    }));
    this.adminService.savePeerReviewAssignments(this.selectedDepartment, payload).subscribe({
      next: (data) => {
        this.assignments = data;
        this.saveLoading = false;
        this.showMessage('Assignments saved successfully.');
        this.loadDepartments();
      },
      error: (err) => {
        const msg = err?.error?.message || 'Failed to save assignments.';
        this.showMessage(msg, true);
        this.saveLoading = false;
      }
    });
  }

  goBack(): void {
    if (window.history.length > 1) {
      this.location.back();
      return;
    }
    this.router.navigate(['/admin']);
  }

  private showMessage(message: string, isError = false): void {
    this.message = message;
    this.isError = isError;
    setTimeout(() => {
      this.message = '';
      this.isError = false;
    }, 3500);
  }
}
