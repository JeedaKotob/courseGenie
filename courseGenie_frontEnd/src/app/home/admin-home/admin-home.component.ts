import { Component, OnInit } from '@angular/core';
import { CourseService } from '../../services/course.service';
import {Course, SyllabusProgress} from '../course.model';
import { Router } from '@angular/router';
import {AdminService} from '../../services/admin.service';

@Component({
  selector: 'app-admin-home',
  standalone: false,
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit {

  courses: Course[] = [];
  animationClass = '';
  syllabusProgress: SyllabusProgress[]=[];

  constructor(
    private courseService: CourseService,
    private router: Router,
    private adminService: AdminService,
  ) {}

  ngOnInit(): void {
    this.loadCourses();
    this.loadSyllabusProgress();
    setTimeout(() => this.animationClass = 'animate-hero', 100);
  }

  loadSyllabusProgress() {
    this.adminService.getSyllabusProgress().subscribe({
      next: data => this.syllabusProgress = data,
      error: err => console.error("Error loading syllabus progress",err)
    });
  }

  loadCourses(): void {
    this.courseService.getAllCourseDTOs().subscribe({
      next: (data) => {
        this.courses = data;
      },
      error: (err) => {
        console.error('Error loading courses:', err);
      }
    });
  }

  navigateToCourse(courseCode: string) {
    this.router.navigate([`/admin/course/${courseCode}`]);
  }

}
