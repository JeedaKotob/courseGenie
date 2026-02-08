import {Component, NgZone, OnInit, ViewEncapsulation} from '@angular/core';
import {Course, Syllabus} from '../home/course.model';
import {SharedDataService} from '../services/shared-data.sevice';
import {SyllabusService} from '../services/syllabus.service';
import {DomSanitizer, SafeHtml, SafeResourceUrl} from '@angular/platform-browser';

declare var html2pdf: any;
@Component({
  selector: 'app-syllabus-render',
  standalone: false,
  templateUrl: './syllabus-render.component.html',
  styleUrl: './syllabus-render.component.scss',

  encapsulation: ViewEncapsulation.Emulated
})
export class SyllabusRenderComponent {
  course: Course | null = null;
  syllabus: Syllabus | null = null;
  trustedHtmlContent: SafeHtml = '';
  pdfUrl: SafeResourceUrl | null = null;
  rawPdfUrl: string = '';
  currentTotal: number = 0;
  submitStatus = {
    type: null as 'success' | 'error' | null,
    message: ''
  };
  private statusTimeout: any;

  constructor(
    private sharedDataService: SharedDataService,
    private syllabusService: SyllabusService,
    private sanitizer: DomSanitizer,
    private ngZone: NgZone
  ) {}

  ngOnInit(): void {
    this.course = this.sharedDataService.selectedCourseValue;
    if (this.course && this.course.sections && this.course.sections.length > 0) {
      this.generateSyllabus(this.course.sections[0].sectionId);
    }
  }

  generateSyllabus(sectionId: number): void {
    this.syllabusService.generateSyllabus(sectionId).subscribe({
      next: (data: Syllabus) => {
        this.syllabus = data;
        this.trustedHtmlContent = this.sanitizer.bypassSecurityTrustHtml(data.content);
        this.syllabusService.getAssessmentTotal(data.syllabusId).subscribe(total=>{
          this.currentTotal=total;
        })
      },
      error: (err) => {
        console.error('Error generating syllabus:', err);
      }
    });
  }

  generatePdf(): void {
    const element = document.getElementById('syllabusPreview');
    if (!element) {
      console.error('Syllabus preview element not found!');
      return;
    }

    // Clone the element so we can modify image paths without affecting the displayed content.
    const clone = element.cloneNode(true) as HTMLElement;

    // Replace incorrect image paths with correct ones.
    // For example, if the backend sends "/syllabus/static/images/" and your images are under "assets/images/",
    // change the URL accordingly:
    clone.innerHTML = clone.innerHTML.replace(/\/syllabus\/static\/images\//g, '/assets/images/');

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
        // Use NgZone to ensure Angular detects the change
        this.ngZone.run(() => {
          this.rawPdfUrl = rawUrl;
          this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(rawUrl);
          console.log('PDF URL generated:', rawUrl);
        });
      })

  }

  downloadSyllabus(): void {
    if (this.rawPdfUrl) {
      const link = document.createElement('a');
      link.href = this.rawPdfUrl;
      link.download = `${this.course?.code || 'course'}-${this.course?.sections?.[0]?.code || 'section'}-syllabus.pdf`;
      document.body.appendChild(link); // For Firefox
      link.click();
      document.body.removeChild(link);
    } else {
      console.error('PDF URL not generated yet');
    }
  }

  showSubmitStatus(type: 'success' | 'error', message: string) {
    this.submitStatus = { type, message };

    if (this.statusTimeout) {
      clearTimeout(this.statusTimeout);
    }

    this.statusTimeout = setTimeout(() => {
      this.submitStatus = { type: null, message: '' };
    }, 2000);
  }

  submitSyllabus(): void {
    if (!this.syllabus) return;
    this.submitStatus = { type: null, message: '' };

    if (this.currentTotal !== 100) {
      this.showSubmitStatus('error', `Cannot submit syllabus. To submit total assessment weight must be exactly 100%. Your section assessment total is ${this.currentTotal}%.`);
      return;
    }
    this.syllabusService.submitSyllabus(this.syllabus.syllabusId)
      .subscribe({
        next: () => {
          if (this.syllabus) {
            this.syllabus.submitted = true;
            this.showSubmitStatus ('success', 'Syllabus submitted successfully!');
          }
        },
        error: err => {
          this.showSubmitStatus ('error', "Unable to submit syllabus. Please try again later");
        }
      });
  }


}
