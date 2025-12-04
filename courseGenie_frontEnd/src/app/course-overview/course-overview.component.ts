import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from '../services/course.service';
import { Observable } from 'rxjs';
import { Course, Section } from '../home/course.model';
import { map, switchMap, distinctUntilChanged } from 'rxjs/operators';

@Component({
  selector: 'app-course-overview',
  standalone: false,
  templateUrl: './course-overview.component.html',
  styleUrls: ['./course-overview.component.scss']
})
export class CourseOverviewComponent implements OnInit {

  course$!: Observable<Course>;
  sections$!: Observable<Section[]>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private courseService: CourseService
  ) {}

  ngOnInit() {
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
}
