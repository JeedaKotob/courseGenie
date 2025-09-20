import {Component, NgZone, OnInit, ViewEncapsulation} from '@angular/core';
import {Course, Syllabus} from '../home/course.model';
import {SharedDataService} from '../services/shared-data.sevice';
import {SyllabusService} from '../services/syllabus.service';
import {DomSanitizer, SafeHtml, SafeResourceUrl} from '@angular/platform-browser';

declare var html2pdf: any;

@Component({
  selector: 'app-syllabus',
  templateUrl: './syllabus.component.html',
  styleUrls: ['./syllabus.component.css'],
  standalone: false,

})
export class SyllabusComponent implements OnInit {
  course: Course | null = null;
  syllabus: Syllabus | null = null;
  trustedHtmlContent: SafeHtml = '';
  pdfUrl: SafeResourceUrl | null = null;
  rawPdfUrl: string = '';

  constructor(
    private sharedDataService: SharedDataService,
    private syllabusService: SyllabusService,
    private sanitizer: DomSanitizer,
    private ngZone: NgZone
  ) {
  }

  ngOnInit(): void {
    this.course = this.sharedDataService.getSharedVariable();
    if (this.course && this.course.sections && this.course.sections.length > 0) {
      this.generateSyllabus(this.course.sections[0].sectionId);
    }
  }

  generateSyllabus(sectionId: number): void {
    this.syllabusService.generateSyllabus(sectionId).subscribe({
      next: (data: Syllabus) => {
        this.syllabus = data;
        this.trustedHtmlContent = this.sanitizer.bypassSecurityTrustHtml(data.content);
      },
      error: (err) => {
        console.error('Error generating syllabus:', err);
      }
    });
  }

}
