import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { AdminService } from '../services/admin.service';
import { CarProgress } from '../home/course.model';

@Component({
  selector: 'app-car-progress',
  standalone: false,
  templateUrl: './car-progress.component.html',
  styleUrls: ['./car-progress.component.scss']
})
export class CarProgressComponent implements OnInit {
  groupedCarProgress: { [key: string]: CarProgress[] } = {};
  expandedCarProgress: Set<number> = new Set<number>();
  message = '';
  loading = false;

  constructor(
    private router: Router,
    private adminService: AdminService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.loadCarProgress();
  }

  loadCarProgress() {
    this.adminService.getCarProgress().subscribe({
      next: (data) => { this.groupedCarProgress = data; },
      error: (err: any) => console.error('Error loading CAR progress', err)
    });
  }

  toggleCarProgress(id: number) {
    if (this.expandedCarProgress.has(id)) {
      this.expandedCarProgress.delete(id);
    } else {
      this.expandedCarProgress.add(id);
    }
  }

  openCar(section: any) {
    this.router.navigate(['/admin/car', section.sectionId]);
  }

  sendReminders() {
    this.loading = true;
    this.message = '';

    this.adminService.sendCarReminders().subscribe({
      next: (response) => {
        this.message = response;
        this.loading = false;

        setTimeout(() => {
          this.message = '';
        }, 3000);
      },
      error: () => {
        this.message = 'Error sending reminders.';
        this.loading = false;

        setTimeout(() => {
          this.message = '';
        }, 3000);
      }
    });
  }

  goBack(): void {
    if (window.history.length > 1) {
      this.location.back();
      return;
    }
    this.router.navigate(['/admin']);
  }
}
