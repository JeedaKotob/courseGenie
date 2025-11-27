import {Component, HostListener, OnInit} from '@angular/core';
import { MenuItem, menuItems } from './menu-config';
import { ActivatedRoute, Router } from '@angular/router';
import { SharedDataService } from '../../services/shared-data.sevice';
import { CourseService } from '../../services/course.service';
import { Course } from '../../home/course.model';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-full-nav',
  standalone: false,
  templateUrl: './full-nav.component.html',
  styleUrl: './full-nav.component.scss'
})
export class FullNavComponent implements OnInit {
  menuItems: MenuItem[] = []; // This will hold filtered menu items
  allMenuItems = menuItems;   // Store all menu items
  openMenus: string[] = [];
  selectedPath: string = '';
  courseCode: string = "";
  sectionCode: string = "";
  sharedValue: Course | null = null;
  isCollapsed: boolean = false;
  isHovered = false;
  isDropdownOpen = false;

  constructor(
    private router: Router,
    private sharedDataService: SharedDataService,
    private courseService: CourseService,
    private authService: AuthService // Add AuthService
  ) { }

  ngOnInit(): void {
    const fullPath = this.router.url;
    const pathSegments = fullPath.split('/');
    this.courseCode = pathSegments[2];
    this.sectionCode = pathSegments[3];

    this.sharedDataService.selectedCourse$.subscribe(value => {
      this.sharedValue = value;
    });

    this.getCourseByCourseCodeAndSectionCode();

    const savedState = localStorage.getItem('sidebarCollapsed');
    if (savedState) {
      this.isCollapsed = savedState === 'true';
    }

    // Filter menu items based on user roles
    this.filterMenuItemsByRole();

    // Subscribe to auth changes to update menu when user role changes
    this.authService.currentUser.subscribe(() => {
      this.filterMenuItemsByRole();
    });
  }

  // Filter menu items based on user roles
  filterMenuItemsByRole(): void {
    this.menuItems = this.allMenuItems.filter(item => {
      // If no roles are required, show to everyone
      if (!item.roles || item.roles.length === 0) {
        return true;
      }

      // If roles are specified, check if the user has any of them
      return this.authService.hasAnyRole(item.roles);
    });
  }

  toggleSidebar(): void {
    this.isCollapsed = !this.isCollapsed;
    // Save sidebar state to localStorage
    localStorage.setItem('sidebarCollapsed', this.isCollapsed.toString());
  }
  get currentUser() {
    return this.sharedDataService.currentUserValue;
  }

  getCourseByCourseCodeAndSectionCode() {
    this.courseService.getCourseByCourseCodeAndSectionCode(this.courseCode, this.sectionCode).subscribe({
      next: (data: Course) => {
        this.updateSharedVariable(data);
      },
      error: (error) => {
        console.error('Error fetching courses', error);
      },
      complete: () => {
        console.log('Course data fetching completed');
      },
    });
  }

  updateSharedVariable(newValue: Course): void {
    this.sharedDataService.setSelectedCourse(newValue);
  }

  toggleMenu(label: string): void {
    if (this.openMenus.includes(label)) {
      this.openMenus = this.openMenus.filter((menu) => menu !== label);
    } else {
      this.openMenus.push(label);
    }
  }

  selectItem(item: MenuItem): void {
    this.selectedPath = item.path != null ? item.path : '';
    if (item.path != null && item.path.indexOf(':courseCode') > -1) {
      this.router.navigate([this.removeDynamicSegments(this.selectedPath), this.courseCode, this.sectionCode]);
    } else {
      this.router.navigate([item.path]);
    }
  }

  isSelected(item: MenuItem): boolean {
    return this.selectedPath === item.path;
  }

  removeDynamicSegments(url: string): string {
    return url.replace(/\/:[^\/]+\/:[^\/]+/, '');
  }
  getcurrentUser() {
    return this.sharedDataService.currentUserValue;
  }


  // Add these methods to your component class
  // Add these properties to your component


// Add this method to get the user's initials for the avatar


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
  // Add these properties and methods to your component


  toggleDropdown(event: Event): void {
    event.stopPropagation();
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  navigateToProfile(event: Event): void {
    event.stopPropagation();
    // Navigate to profile page
    // this.router.navigate(['/profile']);
    this.isDropdownOpen = false;
  }

  navigateToSettings(event: Event): void {
    event.stopPropagation();
    // Navigate to settings page
    // this.router.navigate(['/settings']);
    this.isDropdownOpen = false;
  }



// Close dropdown when clicking outside
  @HostListener('document:click')
  onDocumentClick(): void {
    if (this.isDropdownOpen) {
      this.isDropdownOpen = false;
    }
  }


  doLogout(event?: Event): void {
    if (event) {
      event.stopPropagation();
    }
    // Your existing logout code
    localStorage.clear();
    this.router.navigate(['/login']);
  }


}
