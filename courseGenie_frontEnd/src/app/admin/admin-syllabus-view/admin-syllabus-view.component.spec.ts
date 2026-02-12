import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSyllabusViewComponent } from './admin-syllabus-view.component';

describe('AdminSyllabusViewComponent', () => {
  let component: AdminSyllabusViewComponent;
  let fixture: ComponentFixture<AdminSyllabusViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminSyllabusViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminSyllabusViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
