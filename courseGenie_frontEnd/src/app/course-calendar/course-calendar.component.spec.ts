import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseCalendarComponent } from './course-calendar.component';

describe('CourseCalendarComponent', () => {
  let component: CourseCalendarComponent;
  let fixture: ComponentFixture<CourseCalendarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CourseCalendarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CourseCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
