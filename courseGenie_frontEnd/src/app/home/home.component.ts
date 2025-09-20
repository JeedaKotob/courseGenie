import { Component, OnInit } from '@angular/core';
import { CourseService } from '../services/course.service';
import { Course, CoursesBySemester, Professor } from './course.model';
import { Router } from '@angular/router';
import { SharedDataService } from '../services/shared-data.sevice';

@Component({
  selector: 'app-home',
  standalone: false,

  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {
  animationClass = '';
  coursesBySemester: CoursesBySemester = {};
  semesters: string[] = [];

  navigateToLogin() {
    this.router.navigate(['/login']);
  }


  constructor(private courseService: CourseService, private router: Router) { }

  ngOnInit(): void {
    this.getCoursesByProfessorId();
    setTimeout(() => {
      this.animationClass = 'animate-hero';
    }, 100);

  }


  get currentUser() {
    return SharedDataService.currentUser;
  }

  getCoursesByProfessorId() {
    this.courseService.getCoursesByProfessorId(this.currentUser.professorId).subscribe({
      next: (data: CoursesBySemester) => {
        console.log(this.coursesBySemester)
        this.coursesBySemester = data;
      },
      error: (error) => {
        console.error('Error fetching courses', error);
      },
      complete: () => {
        console.log('Course data fetching completed');
      },
    });
  }

  getSemesters() {
    console.log(this.coursesBySemester);
    return Object.keys(this.coursesBySemester);
  }

  getSectionsList(semester: string) {
    let sections: { sectionId: number,courseName: string, courseCode: string, sectionCode: string, credits: string }[] = [];


    this.coursesBySemester[semester].forEach(course => {
      course.sections.forEach(section => {
        sections.push({
          sectionId: section.sectionId,
          courseName: course.name,
          courseCode: course.code,
          sectionCode: section.code,
          credits: course.credits
        });
      });
    });

    return sections;
  }

  navigateToOverview(sectionId: number) {
    const course = this.findCourseBySectionId(sectionId);
    this.router.navigate(['/overview', course?.code, course?.sections[0].code]);
  }

  findCourseBySectionId(sectionId: number): Course | undefined {
    for (const semester in this.coursesBySemester) {
      if (this.coursesBySemester.hasOwnProperty(semester)) {
        for (let course of this.coursesBySemester[semester]) {
          if (course.sections.some(section => section.sectionId === sectionId)) {
            course.sections = course.sections.filter(e => e.sectionId == sectionId)
            return course; // Return the course that contains the section
          }
        }
      }
    }
    return undefined; // Return undefined if not found
  }

}
