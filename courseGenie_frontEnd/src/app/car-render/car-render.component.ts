import {Component, NgZone, OnInit, ViewEncapsulation} from '@angular/core';
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

  constructor(
    private sharedDataService: SharedDataService,
    private carService: CarService,
    private sanitizer: DomSanitizer,
    private ngZone: NgZone
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

    // 🔥 STEP 1: clone HTML
    const clone = element.cloneNode(true) as HTMLElement;

    // 🔥 STEP 2: FIX IMAGE PATHS (PUT IT RIGHT HERE)
    clone.innerHTML = clone.innerHTML.replace(
      /\/static\/images\//g,
      '/assets/images/'
    );

    // 🔥 STEP 3: generate PDF
    html2pdf()
      .from(clone)
      .outputPdf('blob')
      .then((pdfBlob: Blob) => {
        const rawUrl = URL.createObjectURL(pdfBlob);

        this.ngZone.run(() => {
          this.rawPdfUrl = rawUrl;
          this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(rawUrl);
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
