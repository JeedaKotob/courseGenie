import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { NgZone } from '@angular/core';
import {
  Course,
  ProfessorExamAllocation,
  ProfessorExamRoom,
  ProfessorExamStudent,
  SaveProfessorExamAllocationRequest
} from '../home/course.model';
import { AuthService } from '../services/auth.service';
import { CourseService } from '../services/course.service';
import { ProfessorExamAllocationService } from '../services/professor-exam-allocation.service';

declare var html2pdf: any;

@Component({
  selector: 'app-exam-allocation',
  standalone: false,
  templateUrl: './exam-allocation.component.html',
  styleUrl: './exam-allocation.component.scss'
})
export class ExamAllocationComponent implements OnInit {
  loading = true;
  saving = false;
  errorMessage = '';

  examData: ProfessorExamAllocation | null = null;
  students: ProfessorExamStudent[] = [];
  roomSelection: Record<number, number | null> = {};
  hasChanges = false;
  rawPdfUrl = '';
  pdfUrl: SafeResourceUrl | null = null;

  private professorId: number | null = null;
  private originalSnapshot = '';

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private courseService: CourseService,
    private professorExamAllocationService: ProfessorExamAllocationService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private ngZone: NgZone
  ) {}

  ngOnInit(): void {
    this.professorId = this.authService.currentUserValue?.userId ?? null;
    if (!this.professorId) {
      this.loading = false;
      this.errorMessage = 'Unable to identify logged-in professor.';
      return;
    }

    const courseCode = this.route.snapshot.paramMap.get('courseCode');
    const sectionCode = this.route.snapshot.paramMap.get('sectionCode');
    if (!courseCode || !sectionCode) {
      this.loading = false;
      this.errorMessage = 'Missing course or section details.';
      return;
    }

    this.courseService.getCourseByCourseCodeAndSectionCode(courseCode, sectionCode).subscribe({
      next: (course: Course) => {
        const section = (course.sections || []).find((s) => s.code === sectionCode);
        if (!section) {
          this.loading = false;
          this.errorMessage = 'Section was not found.';
          return;
        }

        this.loadAllocation(section.sectionId);
      },
      error: () => {
        this.loading = false;
        this.errorMessage = 'Unable to load course section details.';
      }
    });
  }

  onAssignmentChange(): void {
    this.hasChanges = this.buildSnapshot() !== this.originalSnapshot;
  }

  autoDistribute(): void {
    if (!this.examData) {
      return;
    }

    const seatPool: number[] = [];
    this.examData.rooms.forEach((room) => {
      const seats = Math.max(0, this.getAvailableSeatsForRoom(room));
      for (let i = 0; i < seats; i += 1) {
        seatPool.push(room.roomId);
      }
    });

    const shuffledSeats = this.shuffleArray([...seatPool]);
    const shuffledStudents = this.shuffleArray([...this.students]);
    const updated: Record<number, number | null> = {};
    shuffledStudents.forEach((student, index) => {
      updated[student.enrollmentId] = shuffledSeats[index] ?? null;
    });

    this.roomSelection = updated;
    this.onAssignmentChange();
  }

  saveAllocations(): void {
    if (!this.examData || !this.professorId || !this.hasChanges) {
      return;
    }

    if (!this.isFullyAssigned()) {
      this.toastr.error('All students must be assigned to a room before saving.');
      return;
    }

    this.saving = true;
    const payload: SaveProfessorExamAllocationRequest = {
      examScheduleId: this.examData.examScheduleId,
      assignments: this.students.map((student) => ({
        enrollmentId: student.enrollmentId,
        roomId: this.roomSelection[student.enrollmentId] as number
      }))
    };

    this.professorExamAllocationService
      .saveBySection(this.examData.sectionId, this.professorId, payload)
      .subscribe({
        next: (saved) => {
          this.setData(saved);
          this.saving = false;
          this.toastr.success('Exam allocation saved.');
        },
        error: (error) => {
          this.saving = false;
          this.toastr.error(error?.error?.message || 'Unable to save exam allocation.');
        }
      });
  }

  generateReportPdf(): void {
    const element = document.getElementById('examAllocationReport');
    if (!element || !this.examData) {
      this.toastr.error('Report content is not ready.');
      return;
    }

    html2pdf()
      .set({
        margin: 0.5,
        filename: `${this.examData.courseCode}-${this.examData.sectionCode}-exam-allocation.pdf`,
        image: { type: 'jpeg', quality: 0.98 },
        html2canvas: { scale: 2 },
        jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' }
      })
      .from(element)
      .outputPdf('blob')
      .then((pdfBlob: Blob) => {
        const rawUrl = URL.createObjectURL(pdfBlob);
        this.ngZone.run(() => {
          this.rawPdfUrl = rawUrl;
          this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(rawUrl);
          this.toastr.success('Exam report PDF generated.');
        });
      })
      .catch(() => {
        this.toastr.error('Unable to generate PDF report.');
      });
  }

  downloadReportPdf(): void {
    if (!this.rawPdfUrl || !this.examData) {
      this.toastr.error('Generate the report first.');
      return;
    }

    const link = document.createElement('a');
    link.href = this.rawPdfUrl;
    link.download = `${this.examData.courseCode}-${this.examData.sectionCode}-exam-allocation.pdf`;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }

  printReport(): void {
    const element = document.getElementById('examAllocationReport');
    if (!element) {
      this.toastr.error('Report content is not ready.');
      return;
    }

    const printWindow = window.open('', '_blank', 'width=1000,height=800');
    if (!printWindow) {
      this.toastr.error('Unable to open print window.');
      return;
    }

    printWindow.document.write(`
      <html>
      <head>
        <title>Exam Allocation Report</title>
        <style>
          body { font-family: Arial, sans-serif; padding: 16px; }
          .report-table { width: 100%; border-collapse: collapse; margin-bottom: 16px; }
          .report-table th, .report-table td { border: 1px solid #ddd; padding: 8px; text-align: left; }
          .report-table th { background: #f5f5f5; }
        </style>
      </head>
      <body>${element.innerHTML}</body>
      </html>
    `);

    printWindow.document.close();
    printWindow.focus();
    printWindow.print();
  }

  getAssignedCount(roomId: number): number {
    return Object.values(this.roomSelection).filter((value) => value === roomId).length;
  }

  getAvailableSeatsForRoom(room: ProfessorExamRoom): number {
    const othersCount = room.assignedCountTotal - room.assignedCountInSection;
    const assignedInCurrentForm = this.getAssignedCount(room.roomId);
    return room.capacity - othersCount - assignedInCurrentForm;
  }

  getProjectedTotalAssigned(room: ProfessorExamRoom): number {
    const othersCount = room.assignedCountTotal - room.assignedCountInSection;
    return othersCount + this.getAssignedCount(room.roomId);
  }

  formatStudentName(student: ProfessorExamStudent): string {
    return `${student.firstName} ${student.lastName}`;
  }

  getStudentsForRoom(roomId: number): ProfessorExamStudent[] {
    return this.students.filter((student) => this.roomSelection[student.enrollmentId] === roomId);
  }

  getUnassignedStudents(): ProfessorExamStudent[] {
    return this.students.filter((student) => !this.roomSelection[student.enrollmentId]);
  }

  getSlotLabel(): string {
    if (!this.examData) {
      return 'N/A';
    }
    if (this.examData.startTime && this.examData.endTime) {
      return `${this.formatTime(this.examData.startTime)} - ${this.formatTime(this.examData.endTime)}`;
    }
    return 'N/A';
  }

  private loadAllocation(sectionId: number): void {
    this.professorExamAllocationService.getBySection(sectionId, this.professorId as number).subscribe({
      next: (data) => {
        this.setData(data);
        this.loading = false;
      },
      error: (error) => {
        this.loading = false;
        this.errorMessage = error?.error?.message || 'No exam schedule available for this section yet.';
      }
    });
  }

  private setData(data: ProfessorExamAllocation): void {
    this.examData = data;
    this.students = data.students || [];
    this.roomSelection = {};
    this.students.forEach((student) => {
      this.roomSelection[student.enrollmentId] = student.assignedRoomId ?? null;
    });

    this.originalSnapshot = this.buildSnapshot();
    this.hasChanges = false;
  }

  private buildSnapshot(): string {
    const sorted = this.students
      .map((student) => ({
        enrollmentId: student.enrollmentId,
        roomId: this.roomSelection[student.enrollmentId] ?? null
      }))
      .sort((a, b) => a.enrollmentId - b.enrollmentId);

    return JSON.stringify(sorted);
  }

  private isFullyAssigned(): boolean {
    return this.students.every((student) => !!this.roomSelection[student.enrollmentId]);
  }

  private shuffleArray<T>(items: T[]): T[] {
    for (let i = items.length - 1; i > 0; i -= 1) {
      const j = Math.floor(Math.random() * (i + 1));
      [items[i], items[j]] = [items[j], items[i]];
    }
    return items;
  }

  private formatTime(value: string): string {
    return value?.slice(0, 5) || value;
  }
}
