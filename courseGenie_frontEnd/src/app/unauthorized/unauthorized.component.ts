import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-unauthorized',
  template: `
    <div class="unauthorized-container">
      <h1>Access Denied</h1>
      <p>You do not have permission to access this page.</p>
      <button class="btn btn-primary" (click)="goBack()">Go Back to Home</button>
    </div>
  `,
  styles: [`
    .unauthorized-container {
      text-align: center;
      padding: 100px 20px;
      max-width: 600px;
      margin: 0 auto;
    }
    h1 {
      color: #dc3545;
      margin-bottom: 20px;
    }
    p {
      margin-bottom: 30px;
      font-size: 18px;
    }
    button {
      padding: 10px 20px;
    }
  `],
  standalone: false
})
export class UnauthorizedComponent {
  constructor(private router: Router) {}

  goBack() {
    this.router.navigate(['/home']);
  }
}
