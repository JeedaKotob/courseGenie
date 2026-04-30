import { Component, OnInit, TemplateRef } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from '../services/course.service';
import { Observable } from 'rxjs';
import { Course, Section } from '../home/course.model';
import { map, switchMap, distinctUntilChanged, take } from 'rxjs/operators';
import { AuthService } from '../services/auth.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SemesterService } from '../services/semester.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-course-overview',
  standalone: false,
  templateUrl: './course-overview.component.html',
  styleUrls: ['./course-overview.component.scss']
})
export class CourseOverviewComponent implements OnInit {
  course$!: Observable<Course>;
  sections$!: Observable<Section[]>;
  isAdmin = false;

  createSectionForm!: FormGroup;
  editSectionForm!: FormGroup;

  isSubmitting = false;
  modalError: string | null = null;
  semesterNames: string[] = [];
  allSemesters: string[] = [];
  selectedSemester: string = 'all';
  professors: { userId: number; firstName: string; lastName: string; userName: string }[] = [];

  selectedSectionId: number | null = null;
  sectionToDelete: Section | null = null;
  createSubmitAttempted = false;
  editSubmitAttempted = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private courseService: CourseService,
    private authService: AuthService,
    private modalService: NgbModal,
    private location: Location,
    private fb: FormBuilder,
    private semesterService: SemesterService
  ) {}

  ngOnInit() {
    this.isAdmin = this.authService.hasRole('ROLE_ADMIN');

    this.createSectionForm = this.buildSectionForm();
    this.editSectionForm = this.buildSectionForm();

    this.course$ = this.route.paramMap.pipe(
      map(pm => pm.get('courseCode')!),
      distinctUntilChanged(),
      switchMap(courseCode => this.courseService.getCourseByCode(courseCode))
    );

    this.sections$ = this.course$.pipe(
      map(course => course.sections ?? [])
    );

    this.loadSectionSemesterFilterOptions();
  }

  private buildSectionForm(): FormGroup {
    return this.fb.group({
      sectionNumber: ['', Validators.required],
      semesterName: [null, Validators.required],
      professorId: [null, Validators.required]
    });
  }

  navigateToSection(courseCode: string, sectionCode: string) {
    this.router.navigate([`/overview/${courseCode}/${sectionCode}`]);
  }

  openAddSectionModal(content: TemplateRef<any>) {
    if (!this.isAdmin || this.modalService.hasOpenModals()) return;

    this.selectedSectionId = null;
    this.modalError = null;
    this.createSubmitAttempted = false;
    this.createSectionForm.reset({
      sectionNumber: '',
      semesterName: null,
      professorId: null
    });

    this.loadSemesterNames();
    this.course$.pipe(take(1)).subscribe({
      next: (course) => this.loadProfessorsForCourse(course)
    });

    this.modalService.open(content, {
      centered: true,
      backdrop: 'static',
      keyboard: true
    });
  }

  openEditSectionModal(section: Section, content: TemplateRef<any>) {
    if (!this.isAdmin || this.modalService.hasOpenModals()) return;

    this.selectedSectionId = section.sectionId;
    this.modalError = null;
    this.editSubmitAttempted = false;

    this.editSectionForm.reset({
      sectionNumber: section.code,
      semesterName: section.semesterName,
      professorId: section.professorId
    });

    this.loadSemesterNames();
    this.course$.pipe(take(1)).subscribe({
      next: (course) => this.loadProfessorsForCourse(course)
    });

    this.modalService.open(content, {
      centered: true,
      backdrop: 'static',
      keyboard: true
    });
  }

  private loadSemesterNames() {
    this.courseService.getSemesterNames().subscribe({
      next: (data) => this.semesterNames = data ?? [],
      error: () => this.semesterNames = []
    });
  }

  private loadSectionSemesterFilterOptions(): void {
    this.semesterService.getAllSemesters().subscribe({
      next: semesters => {
        this.allSemesters = semesters ?? [];
        this.semesterService.getCurrentSemesterName()
          .pipe(catchError(() => of('')))
          .subscribe(currentSemester => {
            this.selectedSemester = currentSemester && this.allSemesters.includes(currentSemester)
              ? currentSemester
              : 'all';
          });
      },
      error: () => {
        this.allSemesters = [];
        this.selectedSemester = 'all';
      }
    });
  }

  getFilteredSections(sections: Section[]): Section[] {
    if (this.selectedSemester === 'all') {
      return sections;
    }
    return sections.filter(section => section.semesterName === this.selectedSemester);
  }

  private loadProfessorsForCourse(course: Course) {
    const departmentName = course.departmentName?.toString()?.trim();

    if (!departmentName) {
      this.professors = [];
      return;
    }

    this.courseService.getProfessorsByDepartment(departmentName).subscribe({
      next: (data) => this.professors = data ?? [],
      error: () => this.professors = []
    });
  }

  closeModal() {
    this.selectedSectionId = null;
    this.sectionToDelete = null;
    this.modalError = null;
    this.modalService.dismissAll();
  }

  private refreshCourse(courseCode: string) {
    this.course$ = this.courseService.getCourseByCode(courseCode);
    this.sections$ = this.course$.pipe(map(course => course.sections ?? []));
  }

  onSectionCreateSubmit() {
    this.createSubmitAttempted = true;

    if (this.createSectionForm.invalid) {
      return;
    }

    const courseCode = this.route.snapshot.paramMap.get('courseCode');
    if (!courseCode) return;

    this.isSubmitting = true;

    const payload = {
      code: this.createSectionForm.value.sectionNumber.trim(),
      semesterName: this.createSectionForm.value.semesterName,
      professorId: Number(this.createSectionForm.value.professorId)
    };

    this.courseService.createSection(courseCode, payload).subscribe({
      next: () => {
        // refresh sections by re-fetching the course
        this.refreshCourse(courseCode);
        this.isSubmitting = false;
        this.closeModal();
      },
      error: (err) => {
        console.error('Failed to create section:', err);
        this.isSubmitting = false;
        this.modalError = err?.error?.message || 'Failed to create section';
      }
    });
  }

  onSectionEditSubmit() {
    this.editSubmitAttempted = true;

    if (this.editSectionForm.invalid) {
      return;
    }

    const courseCode = this.route.snapshot.paramMap.get('courseCode');
    if (!courseCode || this.selectedSectionId == null) return;

    this.isSubmitting = true;

    const payload = {
      code: this.editSectionForm.value.sectionNumber.trim(),
      semesterName: this.editSectionForm.value.semesterName,
      professorId: Number(this.editSectionForm.value.professorId)
    };

    this.courseService.updateSection(courseCode, this.selectedSectionId, payload).subscribe({
      next: () => {
        this.refreshCourse(courseCode);
        this.isSubmitting = false;
        this.closeModal();
      },
      error: (err) => {
        console.error('Failed to update section:', err);
        this.isSubmitting = false;
        this.modalError = err?.error?.message || 'Failed to update section';
      }
    });
  }

  openDeleteConfirmModal(section: Section, content: TemplateRef<any>) {
    if (!this.isAdmin || this.modalService.hasOpenModals()) return;
  
    this.sectionToDelete = section;
    this.modalError = null;
  
    this.modalService.open(content, {
      centered: true,
      backdrop: 'static',
      keyboard: true
    });
  }
  
  confirmDeleteSection() {
    const courseCode = this.route.snapshot.paramMap.get('courseCode');
    if (!courseCode || !this.sectionToDelete) return;
  
    this.isSubmitting = true;
  
    this.courseService.deleteSection(courseCode, this.sectionToDelete.sectionId).subscribe({
      next: () => {
        this.refreshCourse(courseCode);
        this.isSubmitting = false;
        this.closeModal();
      },
      error: (err) => {
        console.error('Failed to delete section:', err);
        this.isSubmitting = false;
        this.modalError = err?.error?.message || 'Failed to delete section';
      }
    });
  }

  goBack(): void {
    if (window.history.length > 1) {
      this.location.back();
      return;
    }
    this.router.navigate(['/admin']);
  }
}
