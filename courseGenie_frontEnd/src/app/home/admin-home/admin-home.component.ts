import { Component, OnInit } from '@angular/core';
import { CourseService } from '../../services/course.service';
import { Course } from '../course.model';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-admin-home',
  standalone: false,
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit {

  courses: Course[] = [];
  groupedCourses: { [department: string]: Course[] } = {};
  expandedDepartments: { [department: string]: boolean } = {};
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
  toolGroups = [
    {
      title: 'Academic Workflows',
      subtitle: 'Monitor submission quality and peer-review progress.',
      tools: ['Peer Review Assignment', 'Syllabus Progress', 'CAR Progress']
    },
    {
      title: 'Assessment Operations',
      subtitle: 'Manage exam logistics and room allocations.',
      tools: ['Exam Room Allocation']
    }
  ];

  toolProgress: { [key: string]: number } = {
    'Syllabus Progress': 0,
    'CAR Progress': 0,
    'Peer Review Assignment': 0
  };

  constructor(
    private courseService: CourseService,
    private adminService: AdminService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.loadCourses();
    this.loadToolProgress();
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
    const grouped = courses.reduce((acc, course) => {
      const departmentName = (course.departmentName || '').trim() || 'Unassigned';
      if (!acc[departmentName]) {
        acc[departmentName] = [];
      }
      acc[departmentName].push(course);
      return acc;
    }, {} as { [department: string]: Course[] });

    this.groupedCourses = grouped;
    Object.keys(grouped).forEach(departmentName => {
      if (this.expandedDepartments[departmentName] === undefined) {
        this.expandedDepartments[departmentName] = true;
      }
    });
  }

  get totalDepartments(): number {
    return Object.keys(this.groupedCourses).length;
  }

  get unassignedCoursesCount(): number {
    return (this.groupedCourses['Unassigned'] || []).length;
  }

  get totalAdminTools(): number {
    return this.adminTools.length;
  }

  getGroupedTools(toolTitles: string[]) {
    return this.adminTools.filter(tool => toolTitles.includes(tool.title));
  }

  getToolProgress(toolTitle: string): number {
    return Math.round(this.toolProgress[toolTitle] || 0);
  }

  getToolProgressStyle(toolTitle: string): { [key: string]: string } {
    const percent = this.getToolProgress(toolTitle);
    return {
      background: `conic-gradient(#ffffff ${percent * 3.6}deg, rgba(255, 255, 255, 0.28) 0deg)`
    };
  }

  isProgressTool(toolTitle: string): boolean {
    return ['Syllabus Progress', 'CAR Progress', 'Peer Review Assignment'].includes(toolTitle);
  }

  getProgressCardClass(toolTitle: string): string {
    if (toolTitle === 'Syllabus Progress') return 'syllabus-progress';
    if (toolTitle === 'CAR Progress') return 'car-progress';
    return 'peer-progress';
  }

  private loadToolProgress(): void {
    this.adminService.getSyllabusProgress().subscribe({
      next: grouped => {
        const all = Object.values(grouped).flat();
        const total = all.reduce((sum, p) => sum + p.totalSections, 0);
        const submitted = all.reduce((sum, p) => sum + p.submittedSyllabi, 0);
        this.toolProgress['Syllabus Progress'] = total === 0 ? 0 : (submitted * 100) / total;
      }
    });

    this.adminService.getCarProgress().subscribe({
      next: grouped => {
        const all = Object.values(grouped).flat();
        const total = all.reduce((sum, p) => sum + p.totalSections, 0);
        const submitted = all.reduce((sum, p) => sum + p.submittedCars, 0);
        this.toolProgress['CAR Progress'] = total === 0 ? 0 : (submitted * 100) / total;
      }
    });

    this.adminService.getPeerReviewProgressSummary().subscribe({
      next: summary => {
        this.toolProgress['Peer Review Assignment'] = summary.completionPercentage || 0;
      }
    });
  }

  navigateToCourse(courseCode: string) {
    this.router.navigate([`/admin/course/${courseCode}`]);
  }

  navigateToTool(route: string): void {
    this.router.navigate([route]);
  }

  toggleDepartment(departmentName: string): void {
    this.expandedDepartments[departmentName] = !this.isDepartmentExpanded(departmentName);
  }

  isDepartmentExpanded(departmentName: string): boolean {
    return this.expandedDepartments[departmentName] !== false;
  }

}
