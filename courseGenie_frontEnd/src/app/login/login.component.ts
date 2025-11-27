import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { SharedDataService } from '../services/shared-data.sevice';
import { Professor } from '../home/course.model';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  usernameError: string | null = null;
  passwordError: string | null = null;
  generalError: string | null = null;
  loading = false;

  private usernameValidationTriggered: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService,
    private sharedDataService: SharedDataService
  ) {}

  validatePassword(): void {
    this.passwordError = !this.password ? 'Password is required' : null;
  }

  validateUsername(): void {
    this.usernameError = !this.username ? 'Username is required' : null;
  }

  doLogin() {
    this.usernameValidationTriggered = true;
    this.validateUsername();
    this.validatePassword();

    if (this.usernameError || this.passwordError) {
      return;
    }

    const payload = {
      username: this.username,
      password: this.password
    };

    this.loading = true;
    this.generalError = null;

    this.authService.doLogin(payload).subscribe({
      next: (loggedInUser: Professor) => {
        this.sharedDataService.setCurrentUser(loggedInUser);
        this.loading = false;
        this.router.navigate(['/home']);
      },
      error: (err) => {
        this.generalError = 'Username or Password is incorrect';
        this.loading = false;
      }
    });
  }
}
