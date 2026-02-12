import {Component, OnInit} from '@angular/core';
import {SyllabusProgress} from '../home/course.model';
import {Router} from '@angular/router';
import {AdminService} from '../services/admin.service';


@Component({
  selector: 'app-syllabus-progress',
  standalone: false,
  templateUrl: './syllabus-progress.component.html',
  styleUrls: ['./syllabus-progress.component.scss']
})
export class SyllabusProgressComponent implements OnInit {
  animationClass = '';
  groupedSyllabusProgress: { [key: string]: SyllabusProgress[] } = {};
  expandedSyllabusProgress: Set<number> = new Set<number>();


  constructor(
    private router: Router,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {
    this.loadSyllabusProgress();
    setTimeout(() => this.animationClass = 'animate-hero', 100);
  }

  loadSyllabusProgress() {
    this.adminService.getSyllabusProgress().subscribe({
      next: (data)  => {this.groupedSyllabusProgress = data;},
      error: (err: any) => console.error("Error loading syllabus progress",err)
    });
  }

  toggleSyllabusProgress(id: number) {
    if (this.expandedSyllabusProgress.has(id)) {
      this.expandedSyllabusProgress.delete(id);
    } else {
      this.expandedSyllabusProgress.add(id);
    }
  }

  openSyllabus(section: any) {
    this.router.navigate(['/admin/syllabus', section.sectionId]);
  }


}
