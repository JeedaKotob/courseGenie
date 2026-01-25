import { Component, OnInit } from '@angular/core';
import { Course, Grade, Student } from '../home/course.model';
import { SharedDataService } from '../services/shared-data.sevice';
import { StudentService } from '../services/student.service';
import { GradeService } from '../services/grade.service';
import { NgForOf, NgIf, CommonModule } from '@angular/common';
import { ButtonComponent } from '../shared/button/button.component';
import {SectionService} from '../services/section.service';

type GradeHighlight = {
  top: Set<string>;
  average: Set<string>;
  low: Set<string>;
};

@Component({
  selector: 'app-grading',
  templateUrl: './grading.component.html',
  styleUrls: ['./grading.component.scss'],

  standalone: false
})
export class GradingComponent implements OnInit {
  course: Course | null = null;
  students: Student[] = [];
  grades: Grade[] = [];
  gradesMatrix: any = {};
  validationErrors: { [studentId: string]: { [assessmentId: number]: string | null } } = {};
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
    this.getAllStudents();
  }

  setGrade(assessmentId: number, studentId: string, event: any) {
    const inputValue = event.value;
    const maxPoints = parseFloat((this.course?.sections?.[0]?.assessments?.find(a => a.assessmentId === assessmentId)?.maxPoints ?? 0).toFixed(2));
    const errorMessage = this.validateScore(inputValue, maxPoints, studentId);

    if (!this.validationErrors[studentId]) {
      this.validationErrors[studentId] = {};
    }

    this.validationErrors[studentId][assessmentId] = errorMessage;

    const index = this.grades.findIndex(e => e.assessmentId === assessmentId && e.studentId === studentId);
    const parsedScore = Number(inputValue);

    if (index !== -1) {
      this.grades[index].score = isNaN(parsedScore) ? -1 : parsedScore;
    } else {
      this.grades.push({
        gradeId: null,
        score: isNaN(parsedScore) ? -1 : parsedScore,
        assessmentId,
        studentId
      });
    }

    if (!this.gradesMatrix[studentId]) {
      this.gradesMatrix[studentId] = {};
    }
    this.gradesMatrix[studentId][assessmentId] = parsedScore;

    this.computeHighlights();
    this.sortStudentsByTotal(); // Re-sort after grade update
  }

  validateScore(value: any, maxPoints: number, studentId: string): string | null {
    const score = Number(value);
    if (isNaN(score)) return 'Score must be a number';
    if (score < 0) return 'Score cannot be negative';
    if (score > maxPoints) return `Score cannot exceed ${maxPoints}`;
    // const totalScore = this.getTotal(studentId) + score;
    // if (totalScore > 100) return 'Total score cannot exceed 100%';
    return null;
  }

  randomizeScores(): void {
    if (!this.course || !this.course.sections || !this.course.sections[0].assessments) {
      console.error('No assessments available to randomize.');
      return;
    }

    for (const student of this.students) {
      for (const assessment of this.course.sections[0].assessments) {
        const max = assessment.maxPoints || 100;
        const randomScore = Math.floor(Math.random() * (max + 1));
        const index = this.grades.findIndex(
          grade => grade.assessmentId === assessment.assessmentId && grade.studentId === student.studentId
        );

        if (index !== -1) {
          this.grades[index].score = randomScore;
        } else {
          this.grades.push({
            gradeId: null,
            score: randomScore,
            assessmentId: assessment.assessmentId,
            studentId: student.studentId
          });
        }

        if (!this.gradesMatrix[student.studentId]) {
          this.gradesMatrix[student.studentId] = {};
        }

        this.gradesMatrix[student.studentId][assessment.assessmentId] = randomScore;

        if (!this.validationErrors[student.studentId]) {
          this.validationErrors[student.studentId] = {};
        }
        this.validationErrors[student.studentId][assessment.assessmentId] = null;
      }
    }

    this.computeHighlights();
    this.sortStudentsByTotal(); // Sort after randomizing
    console.log('Randomized Grades:', this.grades);
  }

  computeHighlights() {
    this.gradeHighlights = {};

    if (!this.course || !this.students.length) return;

    for (const assessment of this.course.sections?.[0]?.assessments || []) {
      const scores = this.students.map(student => ({
        studentId: student.studentId,
        score: this.gradesMatrix[student.studentId]?.[assessment.assessmentId] ?? -1
      })).filter(s => s.score >= 0);

      const sorted = [...scores].sort((a, b) => b.score - a.score);
      const avg = scores.length ? scores.reduce((a, b) => a + b.score, 0) / scores.length : 0;

      const closestToAvg = [...scores].sort((a, b) =>
        Math.abs(a.score - avg) - Math.abs(b.score - avg)
      );

      this.gradeHighlights[assessment.assessmentId] = {
        top: new Set(sorted.slice(0, 2).map(s => s.studentId)),
        average: new Set(closestToAvg.slice(0, 2).map(s => s.studentId)),
        low: new Set(sorted.slice(-2).map(s => s.studentId)),
      };
    }
  }

  getHighlightClass(assessmentId: number, studentId: string): string {
    const highlight = this.gradeHighlights[assessmentId];
    if (!highlight) return '';
    if (highlight.top.has(studentId)) return 'highlight-top';
    if (highlight.average.has(studentId)) return 'highlight-average';
    if (highlight.low.has(studentId)) return 'highlight-low';
    return '';
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
      next: (data: any) => {
        this.showSuccessMessage = true;
      },
      error: (error) => {
        console.error('Error saving grades', error);
      },
      complete: () => {
        console.log('Grades saved successfully');
      }
    });
  }

  dismissSuccessMessage() {
    this.showSuccessMessage = false;
  }

  getTotal(studentId: string) {
    const grades = this.grades.filter(e => e.studentId === studentId);
    return grades
      .filter(g => g.score !== -1)
      .reduce((sum, g) => sum + (g.score ?? 0), 0);
  }

  sortStudentsByTotal() {
    this.students.sort((a, b) => this.getTotal(b.studentId) - this.getTotal(a.studentId));
  }

  getAllStudents() {
    const sectionId = this.course?.sections?.[0]?.sectionId;
    if (!sectionId) return;

    this.sectionService.getStudentsBySection(sectionId).subscribe({
      next: (students: Student[]) => {
        this.students = students;

        // initialize grade matrix
        this.students.forEach(student => {
          if (!this.gradesMatrix[student.studentId]) {
            this.gradesMatrix[student.studentId] = {};
          }
        });

        this.computeHighlights();
        this.sortStudentsByTotal();
      },
      error: err => console.error('Error fetching students', err)
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

}
