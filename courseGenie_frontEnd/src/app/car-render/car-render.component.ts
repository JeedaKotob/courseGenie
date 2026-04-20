import {ChangeDetectorRef, Component, NgZone, OnInit, ViewEncapsulation} from '@angular/core';
import { SharedDataService } from '../services/shared-data.sevice';
import { CarService } from '../services/car.service';
import { DomSanitizer, SafeHtml, SafeResourceUrl } from '@angular/platform-browser';

declare var html2pdf: any;

@Component({
  selector: 'app-car-render',
  standalone: false,
  templateUrl: './car-render.component.html',
  styleUrls: ['./car-render.component.scss'],

  encapsulation: ViewEncapsulation.None
})
export class CarRenderComponent implements OnInit {

  trustedHtmlContent: SafeHtml = '';
  pdfUrl: SafeResourceUrl | null = null;
  rawPdfUrl: string = '';

  car: any;
  course: any;

  /** While true, CAR HTML stays visible so html2pdf can capture it (even when pdfUrl is set). */
  pdfCaptureInProgress = false;

  constructor(
    private sharedDataService: SharedDataService,
    private carService: CarService,
    private sanitizer: DomSanitizer,
    private ngZone: NgZone,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    const course = this.sharedDataService.selectedCourseValue;

    if (!course || !course.sections || course.sections.length === 0) {
      return;
    }

    this.course = course;

    const sectionId = course.sections[0].sectionId;

    // load CAR data (optional if needed later)
    this.carService.getCarBySection(sectionId).subscribe(data => {
      this.car = data;
    });

    // load HTML (MAIN PART)
    this.carService.getCarHtml(sectionId).subscribe(html => {
      this.trustedHtmlContent = this.sanitizer.bypassSecurityTrustHtml(html);
    });
  }

  generatePdf(): void {
    const element = document.getElementById('syllabusPreview');
    if (!element) return;

    this.pdfCaptureInProgress = true;
    this.cdr.detectChanges();

    // One frame so layout runs with .hidden removed (pdfUrl hides the preview otherwise).
    requestAnimationFrame(() => {
      html2pdf()
        .set({
          margin: 0.5,
          filename: 'car.pdf',
          image: { type: 'jpeg', quality: 0.98 },
          html2canvas: { scale: 2, useCORS: true },
          jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' }
        })
        .from(element)
        .outputPdf('blob')
        .then((pdfBlob: Blob) => {
          const rawUrl = URL.createObjectURL(pdfBlob);
          this.ngZone.run(() => {
            this.pdfCaptureInProgress = false;
            this.rawPdfUrl = rawUrl;
            this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(rawUrl);
          });
        })
        .catch((err: unknown) => {
          this.ngZone.run(() => {
            this.pdfCaptureInProgress = false;
          });
          console.error('CAR PDF generation failed:', err);
        });
    });
  }

  download(): void {
    if (!this.rawPdfUrl) return;

    const link = document.createElement('a');
    link.href = this.rawPdfUrl;
    link.download = 'car.pdf';
    link.click();
  }
}
