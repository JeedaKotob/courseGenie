import { Component, OnInit } from '@angular/core';
import { CourseService } from '../../services/course.service';
import { Course } from '../course.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-home',
  standalone: false,
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit {

  courses: Course[] = [];
  groupedCourses: { [department: string]: Course[] } = {};
  animationClass = '';
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
    },
    {
      title: 'CAR Progress',
      description: 'Track CAR submission status across sections and professors.',
      icon: 'bi bi-bar-chart-steps',
      route: '/admin/carProgress',
      cta: 'View Progress'
    },
    {
      title: 'Peer Review Assignment',
      description: 'Assign reviewers to reviewees and auto-pair professors by department.',
      icon: 'bi bi-people',
      route: '/admin/peer-review',
      cta: 'Manage Pairings'
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
        this.groupCoursesByDepartment(data);
      },
      error: (err) => {
        console.error('Error loading courses:', err);
      }
    });
  }

  private groupCoursesByDepartment(courses: Course[]): void {
    this.groupedCourses = courses.reduce((grouped, course) => {
      const departmentName = (course.departmentName || '').trim() || 'Unassigned';
      if (!grouped[departmentName]) {
        grouped[departmentName] = [];
      }
      grouped[departmentName].push(course);
      return grouped;
    }, {} as { [department: string]: Course[] });
  }

  navigateToCourse(courseCode: string) {
    this.router.navigate([`/admin/course/${courseCode}`]);
  }

  navigateToTool(route: string): void {
    this.router.navigate([route]);
  }

}
