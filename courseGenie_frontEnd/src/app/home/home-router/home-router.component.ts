import { Component } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home-router',
  standalone: false,
  templateUrl: './home-router.component.html',
  styleUrl: './home-router.component.scss'
})
export class HomeRouterComponent {

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    const user = this.authService.currentUserValue;
    const userId = user?.userId;

    const stored = userId ? localStorage.getItem(`selectedRole_${userId}`) : null;

    const isAdmin = this.authService.hasRole('ROLE_ADMIN');
    const isProfessor = this.authService.hasRole('ROLE_PROFESSOR');

    if (stored === 'ADMIN' && isAdmin) {
      this.router.navigate(['/admin']); return;
    }

    if (stored === 'PROFESSOR' && isProfessor) {
      this.router.navigate(['/professor']); return;
    }

    if (isAdmin && !isProfessor) {
      this.router.navigate(['/admin']); return;
    }

    if (isProfessor && !isAdmin) {
      this.router.navigate(['/professor']); return;
    }

    if (isAdmin && isProfessor) {
      this.router.navigate(['/choose-role']); return;
    }

    this.router.navigate(['/unauthorized']);
  }

}
