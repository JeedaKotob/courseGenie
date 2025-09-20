import { Component, OnInit } from '@angular/core';
import { SharedDataService } from '../services/shared-data.sevice';
import { SectionService } from '../services/section.service';
import { Course } from '../home/course.model';

@Component({
  selector: 'app-teaching-methodology',
  standalone: false,
  templateUrl: './teaching-methodology.component.html',
  styleUrl: './teaching-methodology.component.scss'
})
export class TeachingMethodologyComponent implements OnInit {
  course: Course | null = null;
  sectionId!: number;
  inputText: string = '';
  isLoading = false;
  showSuccess = false;

  // Header buttons configuration
  headerButtons = [
    {
      label: 'Save Methodology',
      icon: 'save',
      action: 'save-methodology',
      class: 'primary-btn'
    }
  ];

  constructor(
    private sharedService: SharedDataService,
    private sectionService: SectionService
  ) {}

  ngOnInit(): void {
    this.course = this.sharedService.getSharedVariable();
    if (this.course && this.course.sections && this.course.sections.length > 0) {
      this.sectionId = this.course.sections[0].sectionId;
      this.loadMethodology();
    }
  }

  loadMethodology(): void {
    this.sectionService.getTeachingMethodology(this.sectionId).subscribe({
      next: (methodology) => {
        this.inputText = methodology || '';
      },
      error: (err) => {
        console.error('Failed to fetch current methodology:', err);
      }
    });
  }

  submitText(): void {
    if (!this.inputText.trim()) return;
    this.isLoading = true;

    this.sectionService.setTeachingMethodology(this.sectionId, this.inputText).subscribe({
      next: () => {
        this.isLoading = false;
        this.showSuccess = true;
        setTimeout(() => {
          this.showSuccess = false;
        }, 5000); // Auto-hide after 5 seconds
      },
      error: (err) => {
        console.error('Failed to update teaching methodology:', err);
        this.isLoading = false;
      }
    });
  }

  // Handle header button clicks
  handleHeaderAction(action: string): void {
    if (action === 'save-methodology') {
      this.submitText();
    }
  }
}
