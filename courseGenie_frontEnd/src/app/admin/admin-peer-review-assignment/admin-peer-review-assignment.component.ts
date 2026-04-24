import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import {
  PeerReviewAssignment,
  PeerReviewDepartmentOverview,
  PeerReviewPairRequest,
  ProfessorOption,
  RevieweeSectionOption
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
  selectedRevieweeSectionId: number | null = null;
  reviewsPerSection = 1;
  assignments: PeerReviewAssignment[] = [];
  globallyVisible = false;
  unassignedDepartments: string[] = [];

  loading = false;
  autoPairLoading = false;
  saveLoading = false;
  publishLoading = false;
  message = '';
  isError = false;

  constructor(
    private adminService: AdminService,
    private router: Router,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.loadDepartments();
    this.loadPublishStatus();
  }

  get selectedDepartmentProfessors(): ProfessorOption[] {
    return this.departments.find(dept => dept.departmentName === this.selectedDepartment)?.professors ?? [];
  }

  get selectedDepartmentSections(): RevieweeSectionOption[] {
    return this.departments.find(dept => dept.departmentName === this.selectedDepartment)?.revieweeSections ?? [];
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

  loadPublishStatus(): void {
    this.adminService.getPeerReviewPublishStatus().subscribe({
      next: (status) => {
        this.globallyVisible = status.globallyVisible;
        this.unassignedDepartments = status.unassignedDepartments;
      },
      error: () => {
        this.globallyVisible = false;
        this.unassignedDepartments = [];
      }
    });
  }

  onDepartmentChange(): void {
    this.selectedReviewerId = null;
    this.selectedRevieweeSectionId = null;
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
    this.adminService.autoPairPeerReviews(this.selectedDepartment, this.reviewsPerSection).subscribe({
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
    if (!this.selectedReviewerId || !this.selectedRevieweeSectionId) {
      this.showMessage('Select both reviewer and reviewee section first.', true);
      return;
    }
    const revieweeSection = this.selectedDepartmentSections.find(s => s.sectionId === this.selectedRevieweeSectionId);
    if (!revieweeSection) {
      this.showMessage('Selected reviewee section is invalid.', true);
      return;
    }
    if (this.selectedReviewerId === revieweeSection.revieweeId) {
      this.showMessage('Reviewer cannot review their own section.', true);
      return;
    }
    const duplicate = this.assignments.some(a =>
      a.reviewerId === this.selectedReviewerId && a.revieweeSectionId === this.selectedRevieweeSectionId
    );
    if (duplicate) {
      this.showMessage('That directional pair already exists.', true);
      return;
    }

    const reviewer = this.selectedDepartmentProfessors.find(p => p.userId === this.selectedReviewerId);
    if (!reviewer) {
      this.showMessage('Reviewer is invalid for this department.', true);
      return;
    }

    this.assignments = [
      ...this.assignments,
      {
        assignmentId: 0,
        reviewerId: reviewer.userId,
        reviewerName: reviewer.fullName,
        revieweeId: revieweeSection.revieweeId,
        revieweeName: revieweeSection.revieweeName,
        revieweeSectionId: revieweeSection.sectionId,
        courseCode: revieweeSection.courseCode,
        courseName: revieweeSection.courseName,
        sectionCode: revieweeSection.sectionCode,
        departmentName: this.selectedDepartment,
        pairingSource: 'MANUAL',
        progressStatus: 'NOT_STARTED'
      }
    ];
    this.selectedReviewerId = null;
    this.selectedRevieweeSectionId = null;
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
      revieweeSectionId: a.revieweeSectionId
    }));
    this.adminService.savePeerReviewAssignments(this.selectedDepartment, payload).subscribe({
      next: (data) => {
        this.assignments = data;
        this.saveLoading = false;
        this.showMessage('Assignments saved successfully.');
        this.loadDepartments();
        this.loadPublishStatus();
      },
      error: (err) => {
        const msg = err?.error?.message || 'Failed to save assignments.';
        this.showMessage(msg, true);
        this.saveLoading = false;
      }
    });
  }

  publishForAllDepartments(): void {
    if (this.unassignedDepartments.length > 0) {
      this.showMessage('Assign all departments first before publishing visibility.', true);
      return;
    }
    this.publishLoading = true;
    this.adminService.publishPeerReviewForAllDepartments().subscribe({
      next: (status) => {
        this.globallyVisible = status.globallyVisible;
        this.unassignedDepartments = status.unassignedDepartments;
        this.publishLoading = false;
        if (status.unassignedDepartments.length > 0) {
          this.showMessage('Published globally. Warning: some departments still have no assignments.', true);
        } else {
          this.showMessage('Peer review is now visible for all departments.');
        }
      },
      error: () => {
        this.publishLoading = false;
        this.showMessage('Failed to publish peer review visibility.', true);
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
