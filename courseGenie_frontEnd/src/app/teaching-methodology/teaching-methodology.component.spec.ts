import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeachingMethodologyComponent } from './teaching-methodology.component';

describe('TeachingMethodologyComponent', () => {
  let component: TeachingMethodologyComponent;
  let fixture: ComponentFixture<TeachingMethodologyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TeachingMethodologyComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeachingMethodologyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
