import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SyllabusProgressComponent } from './syllabus-progress.component';

describe('SyllabusProgressComponent', () => {
  let component: SyllabusProgressComponent;
  let fixture: ComponentFixture<SyllabusProgressComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SyllabusProgressComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SyllabusProgressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
