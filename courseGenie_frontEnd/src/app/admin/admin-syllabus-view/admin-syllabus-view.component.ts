import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DomSanitizer, SafeHtml, SafeResourceUrl } from '@angular/platform-browser';
import { NgZone } from '@angular/core';
import {SyllabusService} from '../../services/syllabus.service';
import {Syllabus} from '../../home/course.model';
import {SectionService} from '../../services/section.service';


declare var html2pdf: any;

@Component({
  selector: 'app-admin-syllabus-view',
  templateUrl: './admin-syllabus-view.component.html',
  styleUrls: ['./admin-syllabus-view.component.scss'],
  standalone: false,
})
export class AdminSyllabusViewComponent implements OnInit {

  syllabus: Syllabus | null = null;
  trustedHtmlContent: SafeHtml = '';
  pdfUrl: SafeResourceUrl | null = null;
  rawPdfUrl: string = '';
  sectionId!: number;
  section: any;
  courseCode = '';
  courseName = '';
  sectionCode = '';



  constructor(
    private route: ActivatedRoute,
    private syllabusService: SyllabusService,
    private sanitizer: DomSanitizer,
    private ngZone: NgZone,
    private sectionService: SectionService,
  ) {}

  ngOnInit(): void {
    this.sectionId = Number(this.route.snapshot.paramMap.get('sectionId'));
    this.loadSyllabus();
    this.sectionService.getSectionById(this.sectionId).subscribe({
      next: (sec: any) => {
        this.courseCode = sec.courseCode;
        this.courseName = sec.courseName;
        this.sectionCode = sec.code;

      },
      error: (err) => console.error(err)
    });


  }

  loadSyllabus() {
    this.syllabusService.generateSyllabus(this.sectionId).subscribe({
      next: (data: Syllabus) => {
        this.syllabus = data;
        this.trustedHtmlContent =
          this.sanitizer.bypassSecurityTrustHtml(data.content);
      },
      error: (err) => console.error(err)
    });
  }

  generatePdf(): void {
    const element = document.getElementById('syllabusPreview');
    if (!element) return;

    const clone = element.cloneNode(true) as HTMLElement;

    const options = {
      margin: 0.5,
      filename: 'syllabus.pdf',
      image: { type: 'jpeg', quality: 0.98 },
      html2canvas: { scale: 2 },
      jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' }
    };

    html2pdf()
      .set(options)
      .from(clone)
      .outputPdf('blob')
      .then((pdfBlob: Blob) => {
        const rawUrl = URL.createObjectURL(pdfBlob);
        this.ngZone.run(() => {
          this.rawPdfUrl = rawUrl;
          this.pdfUrl =
            this.sanitizer.bypassSecurityTrustResourceUrl(rawUrl);
        });
      });
  }

  downloadSyllabus(): void {
    if (!this.rawPdfUrl) return;

    const link = document.createElement('a');
    link.href = this.rawPdfUrl;
    link.download = 'syllabus.pdf';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }
}
