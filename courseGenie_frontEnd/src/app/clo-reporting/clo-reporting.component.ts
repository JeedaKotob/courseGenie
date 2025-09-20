import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';

import { Benchmark, Course } from '../home/course.model';
import { SharedDataService } from '../services/shared-data.sevice';
import { BenchmarkService } from '../services/benchmark.service';
import {FormsModule} from '@angular/forms';
import {CourseHeaderComponent} from '../course-header/course-header.component';

@Component({
  selector: 'app-clo-reporting',
  templateUrl: './clo-reporting.component.html',
  styleUrls: ['./clo-reporting.component.scss'],
  standalone: false,


})
export class CloReportingComponent implements OnInit {

  course: Course | null = null;
  reportings: any = null;
  bm1Id: number | null = null;
  bm2Id: number | null = null;
  benchmarks: Benchmark[] = [];
  displayedColumns: string[] = ['CLO', 'Description', 'Assessment_Instruments', 'Benchmark_Score', 'Result'];

  constructor(
    private sharedDataService: SharedDataService,
    private benchmarkService: BenchmarkService
  ) { }

  ngOnInit(): void {
    // Retrieve the course from shared data service
    this.course = this.sharedDataService.getSharedVariable();
    if (!this.course) {
      console.error('No course data found in shared data service.');
    }
    this.loadBenchmarks();
  }

  loadBenchmarks(): void {
    this.benchmarkService.findAll().subscribe({
      next: (data: Benchmark[]) => {
        this.benchmarks = data;
        console.log('Benchmarks loaded:', this.benchmarks);
      },
      error: (error) => {
        console.error('Error fetching benchmark data', error);
      },
      complete: () => {
        console.log('Benchmark data fetching completed');
      },
    });
  }

// Add this method to your component class
  getResultStyle(result: string): any {
    if (!result) {
      return {};
    }

    if (result === 'Met') {
      return {
        backgroundColor: '#d4edda',
        color: '#155724'
      };
    } else if (result === 'Not Met') {
      return {
        backgroundColor: '#f8d7da',
        color: '#721c24'
      };
    } else if (result === 'Partially') {
      return {
        backgroundColor: '#fff3cd',
        color: '#856404'
      };
    }

    return {};
  }
  getBenchmarkResults(): void {
    if (this.bm1Id == null || this.bm2Id == null) {
      console.log('One or both benchmark IDs are not selected:', this.bm1Id, this.bm2Id);
      return;
    }
    const sectionId = this.course?.sections?.[0]?.sectionId;
    if (!sectionId) {
      console.error('Section ID not found in course data.');
      return;
    }
    console.log(`Fetching benchmark results for section ${sectionId} with bm1Id ${this.bm1Id} and bm2Id ${this.bm2Id}`);
    this.benchmarkService.getBenchmarkResults(sectionId, this.bm1Id, this.bm2Id).subscribe({
      next: (data: any) => {
        console.log('Benchmark results received:', data);
        this.reportings = data;
      },
      error: (error) => {
        console.error('Error fetching benchmark results', error);
      },
      complete: () => {
        console.log('Benchmark results fetching completed');
      },
    });
  }

  // Returns a color based on the result value.
  getResultColor(result: string): string {
    if (!result) {
      return 'black';
    }
    switch (result.toLowerCase()) {
      case 'met':
        return 'green';
      case 'not met':
        return 'red';
      case 'partially':
        return 'yellow';
      default:
        return 'black';
    }
  }
}
