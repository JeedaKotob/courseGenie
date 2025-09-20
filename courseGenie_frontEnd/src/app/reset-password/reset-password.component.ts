import { Component } from '@angular/core';

@Component({
  selector: 'app-reset-password',
  standalone: false,
  
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.scss'
})
export class ResetPasswordComponent {
  newPassword: string = '';
  passwordConfirmation: string = '';

  // Handle form submission
  onSubmit(): void {
    if (this.newPassword && this.passwordConfirmation) {
      console.log('Username:', this.newPassword);
      console.log('Password:', this.passwordConfirmation);
      // You can add your logic to handle login here
    } else {
      console.log('Please fill in both fields');
    }
  }
}
