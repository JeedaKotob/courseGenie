import { Component, NgZone, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer, SafeHtml, SafeResourceUrl } from '@angular/platform-browser';
import { Location } from '@angular/common';
import { CarService } from '../../services/car.service';
import { SectionService } from '../../services/section.service';

declare var html2pdf: any;

@Component({
  selector: 'app-admin-car-view',
  templateUrl: './admin-car-view.component.html',
  styleUrls: ['./admin-car-view.component.scss'],
  standalone: false
})
export class AdminCarViewComponent implements OnInit {
  trustedHtmlContent: SafeHtml = '';
  pdfUrl: SafeResourceUrl | null = null;
  rawPdfUrl = '';
  sectionId!: number;
  courseCode = '';
  courseName = '';
  sectionCode = '';

  constructor(
    private route: ActivatedRoute,
    private carService: CarService,
    private sanitizer: DomSanitizer,
    private ngZone: NgZone,
    private sectionService: SectionService,
    private location: Location,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.sectionId = Number(this.route.snapshot.paramMap.get('sectionId'));
    this.loadCarHtml();

    this.sectionService.getSectionById(this.sectionId).subscribe({
      next: (sec: any) => {
        this.courseCode = sec.courseCode;
        this.courseName = sec.courseName;
        this.sectionCode = sec.code;
      },
      error: (err) => console.error(err)
    });
  }

  loadCarHtml() {
    this.carService.getCarHtml(this.sectionId).subscribe({
      next: (html: string) => {
        this.trustedHtmlContent = this.sanitizer.bypassSecurityTrustHtml(html);
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
      filename: 'car.pdf',
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
          this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(rawUrl);
        });
      });
  }

  downloadCar(): void {
    if (!this.rawPdfUrl) return;

    const link = document.createElement('a');
    link.href = this.rawPdfUrl;
    link.download = 'car.pdf';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }

  goBack(): void {
    if (window.history.length > 1) {
      this.location.back();
      return;
    }
    this.router.navigate(['/admin/carProgress']);
  }
}
