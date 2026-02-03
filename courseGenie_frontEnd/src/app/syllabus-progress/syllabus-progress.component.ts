import {Component, OnInit} from '@angular/core';
import {SyllabusProgress} from '../home/course.model';
import {Router} from '@angular/router';
import {AdminService} from '../services/admin.service';


@Component({
  selector: 'app-syllabus-progress',
  standalone: false,
  templateUrl: './syllabus-progress.component.html',
  styleUrls: ['./syllabus-progress.component.scss']
})
export class SyllabusProgressComponent implements OnInit {
  animationClass = '';
  groupedSyllabusProgress: { [key: string]: SyllabusProgress[] } = {};

  constructor(
    private router: Router,
    private adminService: AdminService,
  ) {}

  ngOnInit(): void {
    this.loadSyllabusProgress();
    setTimeout(() => this.animationClass = 'animate-hero', 100);
  }

  loadSyllabusProgress() {
    this.adminService.getSyllabusProgress().subscribe({
      next: (data)  => {this.groupedSyllabusProgress = data;},
      error: (err: any) => console.error("Error loading syllabus progress",err)
    });
  }

}
