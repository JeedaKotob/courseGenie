import { Component, OnInit } from '@angular/core';
import { Course, Grade, Student, Enrollment } from '../home/course.model';
import { SharedDataService } from '../services/shared-data.sevice';
import { StudentService } from '../services/student.service';
import { GradeService } from '../services/grade.service';
import { SectionService } from '../services/section.service';

type GradeHighlight = {
  top: Set<number>;
  average: Set<number>;
  low: Set<number>;
};

@Component({
  selector: 'app-grading',
  templateUrl: './grading.component.html',
  styleUrls: ['./grading.component.scss'],
  standalone: false
})
export class GradingComponent implements OnInit {
  course: Course | null = null;
  enrollments: Enrollment[] = [];
  grades: Grade[] = [];
  gradesMatrix: any = {};
  validationErrors: { [enrollmentId: number]: { [assessmentId: number]: string | null } } = {};
  showSuccessMessage: boolean = false;
  gradeHighlights: { [assessmentId: number]: GradeHighlight } = {};

  constructor(
    private sharedDataService: SharedDataService,
    private studentService: StudentService,
    private sectionService: SectionService,
    private gradeService: GradeService
  ) {}

  ngOnInit() {
    this.course = this.sharedDataService.selectedCourseValue;
    this.getEnrollments();

  }


  setGrade(assessmentId: number, enrollmentId: number, event: any) {
    const parsedScore = Number(event.value);

    const index = this.grades.findIndex(
      g => g.assessmentId === assessmentId && g.enrollmentId === enrollmentId
    );

    if (index !== -1) {
      this.grades[index].score = parsedScore;
    } else {
      this.grades.push({
        gradeId: null,
        score: parsedScore,
        assessmentId,
        enrollmentId
      });
    }

    if (!this.gradesMatrix[enrollmentId]) {
      this.gradesMatrix[enrollmentId] = {};
    }

    this.gradesMatrix[enrollmentId][assessmentId] = parsedScore;
  }


  validateScore(value: any, maxPoints: number): string | null {
    const score = Number(value);
    if (isNaN(score)) return 'Score must be a number';
    if (score < 0) return 'Score cannot be negative';
    if (score > maxPoints) return `Score cannot exceed ${maxPoints}`;
    return null;
  }

  randomizeScores(): void {

    if (!this.course?.sections?.[0]?.assessments) return;

    for (const enrollment of this.enrollments) {
      for (const assessment of this.course.sections[0].assessments) {
        const max = assessment.maxPoints || 100;
        const randomScore = Math.floor(Math.random() * (max + 1));
        const index = this.grades.findIndex(
          grade =>
            grade.assessmentId === assessment.assessmentId &&
            grade.enrollmentId === enrollment.enrollmentId
        );

        if (index !== -1) {
          this.grades[index].score = randomScore;
        } else {
          this.grades.push({
            gradeId: null,
            score: randomScore,
            assessmentId: assessment.assessmentId,
            enrollmentId: enrollment.enrollmentId
          });
        }

        if (!this.gradesMatrix[enrollment.enrollmentId]) {
          this.gradesMatrix[enrollment.enrollmentId] = {};
        }

        this.gradesMatrix[enrollment.enrollmentId][assessment.assessmentId] = randomScore;

        if (!this.validationErrors[enrollment.enrollmentId]) {
          this.validationErrors[enrollment.enrollmentId] = {};
        }

        this.validationErrors[enrollment.enrollmentId][assessment.assessmentId] = null;
      }
    }

    this.computeHighlights();
    this.sortStudentsByTotal();
  }

  computeHighlights() {
    this.gradeHighlights = {};
    if (!this.course?.sections?.[0]?.assessments) return;
    for (const assessment of this.course.sections[0].assessments) {

      const scores = this.enrollments.map(enrollment => ({
        enrollmentId: enrollment.enrollmentId,
        score: this.gradesMatrix[enrollment.enrollmentId]?.[assessment.assessmentId] ?? -1
      })).filter(s => s.score >= 0);

      const sorted = [...scores].sort((a, b) => b.score - a.score);

      const avg = scores.length
        ? scores.reduce((a, b) => a + b.score, 0) / scores.length
        : 0;

      const closestToAvg = [...scores].sort((a, b) =>
        Math.abs(a.score - avg) - Math.abs(b.score - avg)
      );

      this.gradeHighlights[assessment.assessmentId] = {
        top: new Set(sorted.slice(0, 2).map(s => s.enrollmentId)),
        average: new Set(closestToAvg.slice(0, 2).map(s => s.enrollmentId)),
        low: new Set(sorted.slice(-2).map(s => s.enrollmentId)),
      };
    }
  }

  getHighlightClass(assessmentId: number, enrollmentId: number): string {
    const highlight = this.gradeHighlights[assessmentId];
    if (!highlight) return '';
    if (highlight.top.has(enrollmentId)) return 'highlight-top';
    if (highlight.average.has(enrollmentId)) return 'highlight-average';
    if (highlight.low.has(enrollmentId)) return 'highlight-low';
    return '';
  }

  getTotal(enrollmentId: number) {
    const grades = this.grades.filter(e => e.enrollmentId === enrollmentId);
    return grades
      .filter(g => g.score !== -1)
      .reduce((sum, g) => sum + (g.score ?? 0), 0);
  }

  sortStudentsByTotal() {
    this.enrollments.sort(
      (a, b) => this.getTotal(b.enrollmentId) - this.getTotal(a.enrollmentId)
    );
  }


  saveGrades() {
    const hasErrors = Object.values(this.validationErrors).some(studentErrors =>
      Object.values(studentErrors).some(error => error !== null)
    );

    if (hasErrors) {
      alert('Please correct all errors before saving.');
      return;
    }

    this.gradeService.saveGrades(this.grades).subscribe({
      next: () => this.showSuccessMessage = true,
      error: (error) => console.error('Error saving grades', error)
    });
  }

  dismissSuccessMessage() {
    this.showSuccessMessage = false;
  }


  getEnrollments() {
    const sectionId = this.course?.sections?.[0]?.sectionId;
    if (!sectionId) return;

    this.sectionService.getEnrollmentsBySection(sectionId).subscribe({
      next: (data: Enrollment[]) => {
        this.enrollments = data;

        this.enrollments.forEach(e => {
          if (!this.gradesMatrix[e.enrollmentId]) {
            this.gradesMatrix[e.enrollmentId] = {};
          }
        });

        // 🔥 LOAD GRADES AFTER ENROLLMENTS
        this.loadGrades(sectionId);
      },
      error: err => console.error(err)
    });
  }



  headerButtons = [
    {
      label: 'Save', // Or appropriate label for the component
      icon: 'save',  // Or appropriate icon
      action: 'save-action', // Or appropriate action
      class: 'primary-btn'
    }
    // Add more buttons as needed
  ];

// Handle header button clicks
  handleHeaderAction(action: string): void {
    if (action === 'save-action') {
      this.saveGrades(); // Call the save method for this component
      // Call appropriate action method for the component
      // e.g., this.saveGrades() or this.saveConfiguration(), etc.
    }
    // Handle other actions as needed
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

        this.computeHighlights();
        this.sortStudentsByTotal();
      },
      error: err => console.error(err)
    });
  }

}
