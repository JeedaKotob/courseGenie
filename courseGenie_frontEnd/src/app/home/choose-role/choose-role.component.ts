import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-choose-role',
  standalone: false,
  templateUrl: './choose-role.component.html',
  styleUrls: ['./choose-role.component.scss']
})
export class ChooseRoleComponent {

  constructor(private router: Router, private authService: AuthService) {}

  choose(role: 'ADMIN' | 'PROFESSOR') {
    const userId = this.authService.currentUserValue?.userId;
    if (!userId) return;

    localStorage.setItem(`selectedRole_${userId}`, role);

    this.router.navigate([`/${role.toLowerCase()}`]);
  }

}
