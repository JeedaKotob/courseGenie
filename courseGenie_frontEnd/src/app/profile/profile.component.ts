import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { finalize } from 'rxjs/operators';
import { User, UserProfileUpdateRequest } from '../home/course.model';
import { AuthService } from '../services/auth.service';
import { SharedDataService } from '../services/shared-data.sevice';
import { UserProfileService } from '../services/user-profile.service';

@Component({
  selector: 'app-profile',
  standalone: false,
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent implements OnInit {
  private readonly successMessageDurationMs = 2500;
  private successMessageTimeoutId: ReturnType<typeof setTimeout> | null = null;
  loading = true;
  saving = false;
  showSuccessMessage = false;
  loadError: string | null = null;
  profile: User | null = null;
  readonly profileForm: FormGroup;

  constructor(
    private location: Location,
    private router: Router,
    private formBuilder: FormBuilder,
    private userProfileService: UserProfileService,
    private authService: AuthService,
    private sharedDataService: SharedDataService
  ) {
    this.profileForm = this.formBuilder.group({
      firstName: [{ value: '', disabled: true }],
      lastName: [{ value: '', disabled: true }],
      userName: [{ value: '', disabled: true }],
      departmentName: [{ value: '', disabled: true }],
      email: [{ value: '', disabled: true }],
      office: ['', [Validators.required, Validators.maxLength(100)]],
      officeHours: ['', [Validators.required, Validators.maxLength(255)]],
      phone: ['', [Validators.required, Validators.maxLength(50)]]
    });
  }

  ngOnInit(): void {
    this.loadProfile();
  }

  loadProfile(): void {
    this.loading = true;
    this.loadError = null;

    this.userProfileService.getCurrentUserProfile()
      .pipe(finalize(() => {
        this.loading = false;
      }))
      .subscribe({
        next: (profile: User) => {
          this.applyProfile(profile);
        },
        error: (error) => {
          this.loadError = error?.error?.message || 'Unable to load your profile right now.';
        }
      });
  }

  saveProfile(): void {
    if (this.profileForm.invalid) {
      this.profileForm.markAllAsTouched();
      return;
    }

    const payload: UserProfileUpdateRequest = {
      office: this.requiredFormValue('office'),
      officeHours: this.requiredFormValue('officeHours'),
      phone: this.requiredFormValue('phone')
    };

    this.saving = true;
    this.userProfileService.updateCurrentUserProfile(payload)
      .pipe(finalize(() => {
        this.saving = false;
      }))
      .subscribe({
        next: (profile: User) => {
          this.applyProfile(profile);
          this.showSuccessMessageForDuration();
        },
        error: (error) => {
          this.loadError = error?.error?.message || 'Unable to save your profile right now.';
        }
      });
  }

  cancelChanges(): void {
    if (!this.profile) {
      return;
    }

    this.profileForm.patchValue({
      office: this.profile.office || '',
      officeHours: this.profile.officeHours || '',
      phone: this.profile.phone || ''
    });
    this.profileForm.markAsPristine();
    this.profileForm.markAsUntouched();
  }

  isInvalid(fieldName: string): boolean {
    const control = this.profileForm.get(fieldName);
    return !!control && control.invalid && (control.touched || control.dirty);
  }

  getFieldError(fieldName: 'email' | 'office' | 'officeHours' | 'phone'): string | null {
    const control = this.profileForm.get(fieldName);
    if (!control || !control.errors || !(control.touched || control.dirty)) {
      return null;
    }

    if (control.errors['required']) {
      return 'This field is required.';
    }

    if (control.errors['email']) {
      return 'Enter a valid email address.';
    }

    if (control.errors['maxlength']) {
      return `This field must be ${control.errors['maxlength'].requiredLength} characters or fewer.`;
    }

    return null;
  }

  private applyProfile(profile: User): void {
    const mergedProfile = {
      ...(this.authService.currentUserValue ?? ({} as User)),
      ...profile
    } as User;

    this.profile = mergedProfile;
    this.profileForm.patchValue({
      firstName: mergedProfile.firstName || '',
      lastName: mergedProfile.lastName || '',
      userName: mergedProfile.userName || '',
      departmentName: mergedProfile.departmentName || 'Unassigned',
      email: mergedProfile.email || '',
      office: mergedProfile.office || '',
      officeHours: mergedProfile.officeHours || '',
      phone: mergedProfile.phone || ''
    });

    this.profileForm.markAsPristine();
    this.profileForm.markAsUntouched();
    this.loadError = null;
    this.authService.setCurrentUser(mergedProfile);
    this.sharedDataService.setCurrentUser(mergedProfile);
  }

  getUserColor(): string {
    if (!this.profile?.firstName) return '#5e72e4';

    const hash = this.profile.firstName.split('')
      .reduce((acc, char) => char.charCodeAt(0) + ((acc << 5) - acc), 0);

    const colors = [
      '#4c6ef5', // Indigo
      '#6610f2', // Purple
      '#6f42c1', // Violet
      '#e83e8c', // Pink
      '#dc3545', // Red
      '#fd7e14', // Orange
      '#ffc107', // Yellow
      '#28a745', // Green
      '#20c997', // Teal
      '#17a2b8'  // Cyan
    ];

    const colorIndex = Math.abs(hash) % colors.length;
    return colors[colorIndex];
  }

  private formValue(fieldName: string): string {
    return (this.profileForm.get(fieldName)?.value ?? '').toString().trim();
  }

  private requiredFormValue(fieldName: string): string {
    return this.formValue(fieldName);
  }

  private showSuccessMessageForDuration(): void {
    this.showSuccessMessage = true;
    if (this.successMessageTimeoutId) {
      clearTimeout(this.successMessageTimeoutId);
    }
    this.successMessageTimeoutId = setTimeout(() => {
      this.showSuccessMessage = false;
      this.successMessageTimeoutId = null;
    }, this.successMessageDurationMs);
  }

  goBack(): void {
    if (this.profileForm.dirty && !this.saving) {
      const shouldLeave = window.confirm('You have unsaved changes. Discard them and go back?');
      if (!shouldLeave) {
        return;
      }
    }

    if (window.history.length > 1) {
      this.location.back();
      return;
    }
  
    this.router.navigate(['/home']);
  }
}