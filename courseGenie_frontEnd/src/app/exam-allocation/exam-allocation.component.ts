import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
  notifying = false;
  errorMessage = '';
  successMessage = '';
  inlineErrorMessage = '';

  examData: ProfessorExamAllocation | null = null;
  students: ProfessorExamStudent[] = [];
  roomSelection: Record<number, number | null> = {};
  hasChanges = false;

  private professorId: number | null = null;
  private originalSnapshot = '';

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private courseService: CourseService,
    private professorExamAllocationService: ProfessorExamAllocationService
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
      this.showInlineErrorMessage('All students must be assigned to a room before saving.');
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
          this.showSuccessMessage('Exam allocation saved successfully.');
        },
        error: (error) => {
          this.saving = false;
          this.showInlineErrorMessage(error?.error?.message || 'Unable to save exam allocation.');
        }
      });
  }

  private generateReportPdfBlob(): Promise<Blob> {
    const element = document.getElementById('examAllocationReport');
    if (!element || !this.examData) {
      return Promise.reject(new Error('Report content is not ready.'));
    }

    const container = document.createElement('div');
    container.style.position = 'absolute';
    container.style.left = '-9999px';
    container.style.top = '0';
    container.style.width = '800px';

    const clone = element.cloneNode(true) as HTMLElement;

    clone.classList.remove('report-export-only');
    clone.style.display = 'block';
    clone.style.visibility = 'visible';
    clone.style.opacity = '1';

    container.appendChild(clone);
    document.body.appendChild(container);

    return new Promise<Blob>((resolve, reject) => {
      setTimeout(() => {
        const opt = {
          margin: 0.5,
          filename: `${this.examData?.courseCode}-allocation.pdf`,
          image: { type: 'jpeg', quality: 0.98 },
          html2canvas: {
            scale: 2,
            useCORS: true,
            logging: false,
            letterRendering: true
          },
          jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' },
          pagebreak: {
            mode: ['css', 'legacy'],
            avoid: ['tr', '.report-room-block']
          }
        };

        html2pdf()
          .set(opt)
          .from(clone)
          .toPdf()
          .output('blob')
          .then((pdfBlob: Blob) => {
            document.body.removeChild(container);
            resolve(pdfBlob);
          })
          .catch((err: any) => {
            document.body.removeChild(container);
            reject(err);
          });
      }, 150);
    });
  }

  downloadReportPdf(): void {
    if (!this.examData) {
      this.showInlineErrorMessage('Report content is not ready.');
      return;
    }

    this.generateReportPdfBlob()
      .then((pdfBlob) => {
        const rawUrl = URL.createObjectURL(pdfBlob);
        const link = document.createElement('a');
        link.href = rawUrl;
        link.download = `${this.examData?.courseCode}-${this.examData?.sectionCode}-exam-allocation.pdf`;
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(rawUrl);
      })
      .catch(() => {
        this.showInlineErrorMessage('Unable to generate PDF report.');
      });
  }

  notifyStudents(): void {
    if (!this.examData || !this.professorId) {
      this.showInlineErrorMessage('Allocation data is not ready.');
      return;
    }

    this.notifying = true;
    this.professorExamAllocationService
      .notifyStudents(this.examData.sectionId, this.professorId)
      .subscribe({
        next: (message) => {
          this.notifying = false;
          this.showSuccessMessage(message || 'Student notifications sent successfully.');
        },
        error: (error) => {
          this.notifying = false;
          this.showInlineErrorMessage(error?.error || 'Unable to notify students.');
        }
      });
  }

  printReport(): void {
    const element = document.getElementById('examAllocationReport');
    if (!element) {
      this.showInlineErrorMessage('Report content is not ready.');
      return;
    }

    const printWindow = window.open('', '_blank', 'width=1000,height=800');
    if (!printWindow) {
      this.showInlineErrorMessage('Unable to open print window.');
      return;
    }

    printWindow.document.write(`
      <html>
      <head>
        <title>Exam Allocation Report</title>
        <style>
          body { font-family: Arial, sans-serif; padding: 16px; }
          .report-room-block { margin-bottom: 16px; break-inside: avoid-page; page-break-inside: avoid; }
          .report-table { width: 100%; border-collapse: collapse; margin-bottom: 16px; table-layout: fixed; }
          .report-table th, .report-table td { border: 1px solid #ddd; padding: 8px; text-align: left; }
          .report-table th { background: #f5f5f5; }
          .report-table thead { display: table-header-group; }
          .report-table tr { break-inside: avoid-page; page-break-inside: avoid; }
          .report-table th:nth-child(1), .report-table td:nth-child(1) { width: 22%; }
          .report-table th:nth-child(2), .report-table td:nth-child(2) { width: 18%; }
          .report-table th:nth-child(3), .report-table td:nth-child(3) { width: 24%; }
          .report-table th:nth-child(4), .report-table td:nth-child(4) { width: 36%; }
          @media print {
            h3, h4, p { break-after: avoid-page; page-break-after: avoid; }
          }
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

  getRoomStudentCount(roomId: number): number {
    return this.getStudentsForRoom(roomId).length;
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

  private showSuccessMessage(message: string): void {
    this.successMessage = message;
    this.inlineErrorMessage = '';
    setTimeout(() => {
      this.successMessage = '';
    }, 3500);
  }

  private showInlineErrorMessage(message: string): void {
    this.inlineErrorMessage = message;
    this.successMessage = '';
    setTimeout(() => {
      this.inlineErrorMessage = '';
    }, 4000);
  }
}
