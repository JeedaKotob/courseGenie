import { Component, Input } from '@angular/core';
import { CLO, Course } from '../../home/course.model';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SharedDataService } from '../../services/shared-data.sevice';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CloService } from '../../services/clo.service';
import { CloListingComponent } from '../../clo-listing/clo-listing.component';

@Component({
  selector: 'app-create-clo',
  standalone: false,

  templateUrl: './create-clo.component.html',
  styleUrl: './create-clo.component.scss'
})
export class CreateCLOComponent {
  @Input() course: Course | null = null;
  cloForm: FormGroup;

  isSubmitted: boolean = false;


  constructor(private sharedDataService: SharedDataService, private modalService: NgbModal, private formBuilder: FormBuilder, private cloService: CloService) {
    this.cloForm = this.formBuilder.group({
      name: new FormControl(null, [Validators.required]),
      description: new FormControl(null, [Validators.required]),
    });
  }

  close() {
    this.modalService.dismissAll();
  }

  openVerticallyCentered(content: any) {
    this.modalService.open(content, {
      backdrop: 'static',
      keyboard: false,
      centered: true
    });
  }

  saveCLO() {
    this.isSubmitted = true;
    let payload = {
      name: this.cloForm.value.name,
      description: this.cloForm.value.description,
      courseId: this.course?.courseId,
    }
    this.cloService.createCLO(payload).subscribe({
      next: (data: CLO) => {
        this.course?.clos?.push(data);
        this.sharedDataService.setSharedVariable(this.course == null ? null : this.course);
        CloListingComponent.updateList.next(true);
        this.close();
      },
      error: (error) => {
        console.error('Error fetching courses', error);
        this.isSubmitted = false;
      },
      complete: () => {
        this.isSubmitted = false;
      },
    });

  }
}
