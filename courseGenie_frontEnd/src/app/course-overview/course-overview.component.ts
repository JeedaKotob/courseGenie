import { Component, OnInit, TemplateRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from '../services/course.service';
import { Observable } from 'rxjs';
import { Course, Section } from '../home/course.model';
import { map, switchMap, distinctUntilChanged } from 'rxjs/operators';
import { AuthService } from '../services/auth.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

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

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private courseService: CourseService,
    private authService: AuthService,
    private modalService: NgbModal
  ) {}

  ngOnInit() {
    this.isAdmin = this.authService.hasRole('ROLE_ADMIN');

    this.course$ = this.route.paramMap.pipe(
      map(pm => pm.get('courseCode')!),
      distinctUntilChanged(),
      switchMap(courseCode => this.courseService.getCourseByCode(courseCode))
    );

    this.sections$ = this.course$.pipe(
      map(course => course.sections ?? [])
    );
  }

  navigateToSection(courseCode: string, sectionCode: string) {
    this.router.navigate([`/overview/${courseCode}/${sectionCode}`]);
  }
  openAddSectionModal(content: TemplateRef<any>) {
    if (!this.isAdmin || this.modalService.hasOpenModals()) return;
  
    this.modalService.open(content, {
      centered: true,
      backdrop: 'static',
      keyboard: true
    });
  }
  
  closeModal() {
    this.modalService.dismissAll();
  }
  
  onSectionCreateSubmit() {
    // US6 scope: open/close popup only
    this.closeModal();
  }
  
}
