import { Component, OnInit } from '@angular/core';
import { Course, Grade, Student, Enrollment } from '../home/course.model';
import { SharedDataService } from '../services/shared-data.sevice';
import { StudentService } from '../services/student.service';
import { GradeService } from '../services/grade.service';
import { CommonModule } from '@angular/common';
import { NgChartsModule } from 'ng2-charts';
import { ChartConfiguration, ChartOptions } from 'chart.js';
import {SectionService} from '../services/section.service';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.scss'],
  standalone: true,
  imports: [CommonModule, NgChartsModule],
})
export class StatisticsComponent implements OnInit {
  course: Course | null = null;
  enrollments: Enrollment[] = [];
  grades: Grade[] = [];
  gradesMatrix: any = {};

  scoreLabels: string[] = [];
  scoreCounts: number[] = [];
  barChartData!: ChartConfiguration<'bar'>['data'];

  topChartData!: ChartConfiguration<'bar'>['data'];
  averageChartData!: ChartConfiguration<'bar'>['data'];
  bottomChartData!: ChartConfiguration<'bar'>['data'];

  barChartOptions: ChartOptions<'bar'> = {
    responsive: true,
    plugins: {
      legend: { display: false },
      title: { display: true, text: 'Score Distribution' }
    },
    scales: {
      x: {},
      y: {
        beginAtZero: true,
        ticks: { stepSize: 1, precision: 0 }
      }
    }
  };

  horizontalBarOptions: ChartOptions<'bar'> = {
    indexAxis: 'y',
    responsive: true,
    plugins: {
      legend: { display: false },
      title: { display: false }
    },
    scales: {
      x: {
        beginAtZero: true,
        max: 100,
        ticks: { stepSize: 10 }
      },
      y: {
        ticks: { font: { size: 12 } }
      }
    }
  };

  pieChartData: ChartConfiguration<'pie'>['data'] = {
    labels: ['Pass', 'Fail'],
    datasets: [{
      data: [0, 0],
      backgroundColor: ['#198754', '#dc3545'],
      hoverBackgroundColor: ['#157347', '#bb2d3b'],
    }]
  };

  pieChartOptions: ChartOptions<'pie'> = {
    responsive: true,
    plugins: {
      legend: { position: 'bottom' },
      title: { display: false }
    }
  };

  constructor(
    private sharedDataService: SharedDataService,
    private studentService: StudentService,
    private gradeService: GradeService,
    private sectionService: SectionService
  ) {}

  ngOnInit(): void {
    this.course = this.sharedDataService.selectedCourseValue;
    const sectionId = this.course?.sections?.[0]?.sectionId;
    if (!sectionId) return;
    this.getEnrollments(sectionId);
  }

  // Add this method to your component class
  calculateBarWidth(count: number): number {
    if (!this.enrollments.length) return 0;

    // Calculate percentage (max 100%)
    const percentage = (count / this.enrollments.length) * 100;

    // Ensure bars have a minimum width to be visible
    return percentage === 0 ? 0 : Math.max(percentage, 4);
  }
  getAllStudents(): void {
    const sectionId = this.course?.sections?.[0]?.sectionId;
    if (!sectionId) return;

    this.sectionService.getEnrollmentsBySection(sectionId).subscribe({
      next: (enrollments: Enrollment[]) => {
        this.enrollments = enrollments;
        this.generateChart();
      },
      error: err => console.error(err)
    });
  }


  getTotalScore(enrollmentId: number): number {
    return Object.values(this.gradesMatrix[enrollmentId] || {})
      .filter((score: any) => typeof score === 'number')
      .reduce((a: number, b: number) => a + b, 0);
  }


  get totalStudents(): number {
    return this.enrollments.length;
  }

  get averageScore(): number {
    if (!this.totalStudents) return 0;
    const total = this.enrollments.reduce(
      (sum, e) => sum + this.getTotalScore(e.enrollmentId),
      0
    );
    return +(total / this.totalStudents).toFixed(2);
  }

  get highestScore(): number {
    return Math.max(...this.enrollments.map(e =>
      this.getTotalScore(e.enrollmentId)
    ), 0);
  }

  get lowestScore(): number {
    return Math.min(...this.enrollments.map(e =>
      this.getTotalScore(e.enrollmentId)
    ), 100);
  }

  get passRate(): number {
    const passed = this.enrollments.filter(e =>
      this.getTotalScore(e.enrollmentId) >= 60
    ).length;

    return +((passed / this.totalStudents) * 100).toFixed(2);
  }


  get failRate(): number {
    const failed = this.enrollments.filter(e => this.getTotalScore(e.enrollmentId) < 60).length;
    return +((failed / this.totalStudents) * 100).toFixed(2);
  }

  generateChart(): void {
    const ranges = Array(10).fill(0);
    let pass = 0;
    let fail = 0;

    this.enrollments.forEach(enrollment => {
      const total = this.getTotalScore(enrollment.enrollmentId);
      const index = Math.min(Math.floor(total / 10), 9);
      ranges[index]++;
      if (total >= 60) pass++;
      else fail++;
    });

    this.scoreLabels = ranges.map((_, i) => `${i * 10}-${i * 10 + 9}`);
    this.scoreCounts = ranges;

    this.barChartData = {
      labels: this.scoreLabels,
      datasets: [{
        data: this.scoreCounts,
        backgroundColor: '#007bff'
      }]
    };

    this.pieChartData.datasets[0].data = [pass, fail];
    this.topChartData = this.prepareHorizontalData(this.top5);
    this.averageChartData = this.prepareHorizontalData(this.average5);
    this.bottomChartData = this.prepareHorizontalData(this.bottom5);
  }

  prepareHorizontalData(list: any[]): ChartConfiguration<'bar'>['data'] {
    return {
      labels: list.map(s => `${s.enrollment.firstName} ${s.enrollment.lastName}`),
      datasets: [{
        data: list.map(s => s.total),
        backgroundColor: '#0d6efd'
      }]
    };
  }

  get sortedStudents(): { enrollment: Enrollment; total: number }[] {
    return this.enrollments
      .map(e => ({
        enrollment: e,
        total: this.getTotalScore(e.enrollmentId)
      }))
      .sort((a, b) => b.total - a.total);
  }





  get top5(): any[] {
    return this.sortedStudents.slice(0, 5);
  }

  get bottom5(): any[] {
    return this.sortedStudents.slice(-5);
  }

  get average5(): any[] {
    const mid = Math.floor(this.sortedStudents.length / 2);
    return this.sortedStudents.slice(mid - 2, mid + 3);
  }

  get gradeDistribution(): { label: string; count: number }[] {
    const grades = [
      { label: 'A', min: 94, max: 100, count: 0 },
      { label: 'A-', min: 90, max: 93.99, count: 0 },
      { label: 'B+', min: 87, max: 89.99, count: 0 },
      { label: 'B', min: 83, max: 86.99, count: 0 },
      { label: 'B-', min: 80, max: 82.99, count: 0 },
      { label: 'C+', min: 77, max: 79.99, count: 0 },
      { label: 'C', min: 73, max: 76.99, count: 0 },
      { label: 'C-', min: 70, max: 72.99, count: 0 },
      { label: 'D', min: 60, max: 69.99, count: 0 },
      { label: 'F', min: 0, max: 59.99, count: 0 },
    ];

    this.enrollments.forEach(e => {
      const total = this.getTotalScore(e.enrollmentId);
      const grade = grades.find(g => total >= g.min && total <= g.max);
      if (grade) grade.count++;
    });

    return grades.map(({ label, count }) => ({ label, count }));
  }

  getEnrollments(sectionId: number) {
    this.sectionService.getEnrollmentsBySection(sectionId).subscribe({
      next: (data: Enrollment[]) => {
        this.enrollments = data;

        // initialize matrix
        this.enrollments.forEach(e => {
          if (!this.gradesMatrix[e.enrollmentId]) {
            this.gradesMatrix[e.enrollmentId] = {};
          }
        });

        this.loadGrades(sectionId);
      },
      error: err => console.error(err)
    });
  }

  loadGrades(sectionId: number) {
    this.gradeService.getGradesBySection(sectionId).subscribe({
      next: (grades: Grade[]) => {
        this.grades = grades;

        grades.forEach(g => {
          if (!this.gradesMatrix[g.enrollmentId]) {
            this.gradesMatrix[g.enrollmentId] = {};
          }

          this.gradesMatrix[g.enrollmentId][g.assessmentId] = g.score;
        });

        this.generateChart();
      },
      error: err => console.error(err)
    });
  }



}
