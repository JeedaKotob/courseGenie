import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {SpinnerModule} from '@coreui/angular';

@Component({
  selector: 'app-login',
  standalone: false,

  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  usernameError: string | null = null;
  passwordError: string | null = null;
  generalError: string | null = null;

  private usernameValidationTriggered: boolean = false; // To track if username validations should show

  constructor(private authService: AuthService, private router: Router, private toastr: ToastrService) {
  }

  // validateUsername(): void {
  //   if (!this.username) {
  //     this.usernameError = 'Username is required';
  //   } else if (!this.username.includes('cad')) {
  //     this.usernameError = 'Invalid Username';
  //   } else if (this.username.length < 6) {
  //     this.usernameError = 'Invalid Username';
  //   } else {
  //     this.usernameError = null;
  //   }
  // }


  validatePassword(): void {
    this.passwordError = !this.password ? 'Password is required' : null;
  }

  validateUsername(): void {
    this.passwordError = !this.password ? 'Password is required' : null;
  }

  loading = false;

  doLogin() {
    this.usernameValidationTriggered = true;
    this.validateUsername();
    this.validatePassword();

    if (this.usernameError || this.passwordError) {
      return;
    }

    let payload = {
      username: this.username,
      password: this.password
    };

    this.loading = true;

    // Navigate immediately to Overview page with a query param like loading=true
    this.router.navigate(['/overview'], { queryParams: { loading: 'true' } });

    this.authService.doLogin(payload).subscribe({
      next: (data) => {
        this.loading = false;
        this.router.navigate(['/home']);  // or wherever you want after successful login
      },
      error: () => {
        this.generalError = 'Username or Password is incorrect';
        this.loading = false;
        // Optionally, navigate back to login if error
        this.router.navigate(['/login']);
      }
    });
  }
}
