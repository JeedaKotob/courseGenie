import {Component, HostListener} from '@angular/core';
import { SharedDataService } from '../../services/shared-data.sevice';
import { Router } from '@angular/router';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: false,
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  constructor(private router: Router, private sharedDataService: SharedDataService, private authService: AuthService) { }

  get currentUser() {
    return this.sharedDataService.currentUserValue;
  }

  isMultiRole(){
    return (
      this.authService.hasRole('ROLE_ADMIN') && this.authService.hasRole('ROLE_PROFESSOR')
    );
  }

  doLogout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
  // Add these methods to your component class

  switchRole(): void {
    const user = this.currentUser;
    if (!user) return;

    const userId = user.userId;
    const key = `selectedRole_${userId}`;

    const current = localStorage.getItem(key);

    if (current === 'ADMIN') {
      localStorage.setItem(key, 'PROFESSOR');
      this.router.navigate(['/professor']);
    } else if (current === 'PROFESSOR') {
      localStorage.setItem(key, 'ADMIN');
      this.router.navigate(['/admin']);
    } else {
      this.router.navigate(['/choose-role']);
    }
    this.isDropdownOpen = false;
  }


// Generate initials from user's name
  getInitials(): string {
    if (!this.currentUser) return '';

    const firstInitial = this.currentUser.firstName ? this.currentUser.firstName.charAt(0) : '';
    const lastInitial = this.currentUser.lastName ? this.currentUser.lastName.charAt(0) : '';

    return (firstInitial + lastInitial).toUpperCase();
  }

// Generate a consistent color based on user's name
  getUserColor(): string {
    if (!this.currentUser || !this.currentUser.firstName) return '#5e72e4'; // Default color

    // Simple hash function to generate a color based on the user's name
    const hash = this.currentUser.firstName.split('')
      .reduce((acc, char) => char.charCodeAt(0) + ((acc << 5) - acc), 0);

    // List of pleasant, accessible colors
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

    // Use the hash to select a color
    const colorIndex = Math.abs(hash) % colors.length;
    return colors[colorIndex];
  }
  // Add these to your component
  isDropdownOpen = false;

  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  navigateToProfile(event: Event) {
    event.stopPropagation();
    this.router.navigate(['/profile']);
    this.isDropdownOpen = false;
  }

  navigateToSettings(event: Event) {
    event.stopPropagation();
    this.router.navigate(['/settings']);
    this.isDropdownOpen = false;
  }
// Close dropdown when clicking outside (add to your component)
  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
    const target = event.target as HTMLElement;
    const dropdown = document.querySelector('.custom-dropdown');
    const toggleButton = document.querySelector('.d-flex.align-items-center.cursor-pointer');

    if (
      this.isDropdownOpen &&
      dropdown &&
      toggleButton &&
      !dropdown.contains(target) &&
      !toggleButton.contains(target)
    ) {
      this.isDropdownOpen = false;
    }
  }




}
