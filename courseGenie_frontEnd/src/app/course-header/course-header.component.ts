// course-header.component.ts
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Course } from '../home/course.model';

export interface ActionButton {
  label: string;
  icon: string;
  action: string;
  class?: string;
  hidden?: boolean;
}

@Component({
  selector: 'app-course-header',
  templateUrl: './course-header.component.html',
  styleUrls: ['./course-header.component.scss'],
  standalone: false
})
export class CourseHeaderComponent {
  @Input() course: Course | null = null;
  @Input() buttons: ActionButton[] = [];

  @Output() buttonClick = new EventEmitter<string>();

  onButtonClick(action: string): void {
    this.buttonClick.emit(action);
  }
}
