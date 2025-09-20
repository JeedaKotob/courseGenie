import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgIf, NgStyle} from '@angular/common';

@Component({
  selector: 'app-reset-password-request',
  templateUrl: './reset-password-request.component.html',
  standalone: false,
  styleUrl: './reset-password-request.component.scss'
})
export class ResetPasswordRequestComponent {
  username: string = '';
  oldPassword: string = '';
  newPassword: string = '';
  confirmPassword: string = '';

  // Validation flags
  hasMinLength: boolean = false;
  hasUppercase: boolean = false;
  hasLowercase: boolean = false;
  hasNumber: boolean = false;
  hasSpecialChar: boolean = false;

  passwordStrengthPercent: number = 0;
  strengthBarColor: string = 'red';

  checkPasswordStrength(): void {
    const password = this.newPassword;
    this.hasMinLength = password.length >= 8;
    this.hasUppercase = /[A-Z]/.test(password);
    this.hasLowercase = /[a-z]/.test(password);
    this.hasNumber = /\d/.test(password);
    this.hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);

    const strengthCount = [
      this.hasMinLength,
      this.hasUppercase,
      this.hasLowercase,
      this.hasNumber,
      this.hasSpecialChar
    ].filter(Boolean).length;

    this.passwordStrengthPercent = (strengthCount / 5) * 100;

    if (strengthCount <= 2) {
      this.strengthBarColor = 'red';
    } else if (strengthCount === 3 || strengthCount === 4) {
      this.strengthBarColor = 'orange';
    } else if (strengthCount === 5) {
      this.strengthBarColor = 'green';
    }
  }

  onSubmit(form: any): void {
    if (form.valid && this.newPassword === this.confirmPassword) {
      console.log('Form submitted successfully.');
      // Handle submission logic here
    } else {
      console.log('Form invalid or passwords do not match.');
    }
  }
}
