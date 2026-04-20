import { Component, OnInit } from '@angular/core';
import { CourseService } from '../../services/course.service';
import {Course, SyllabusProgress} from '../course.model';
import { Router } from '@angular/router';

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
  adminTools = [
    {
      title: 'Exam Room Allocation',
      description: 'Assign available rooms for scheduled exams and manage conflicts.',
      icon: 'bi bi-building',
      route: '/examRooms',
      cta: 'Manage Exam Rooms'
    },
    {
      title: 'Syllabus Progress',
      description: 'Track syllabus submission status across sections and professors.',
      icon: 'bi bi-clipboard-check',
      route: '/admin/syllabusProgress',
      cta: 'View Progress'
    }
  ];

  constructor(
    private courseService: CourseService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.loadCourses();
    setTimeout(() => this.animationClass = 'animate-hero', 100);
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

  navigateToTool(route: string): void {
    this.router.navigate([route]);
  }

}
