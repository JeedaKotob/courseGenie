import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // Required for animations
import { ToastrModule } from 'ngx-toastr';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ResetPasswordRequestComponent } from './reset-password-request/reset-password-request.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { ProfHomeComponent } from './home/prof-home/prof-home.component';
import { OverviewComponent } from './overview/overview.component';
import { GuestLayoutComponent } from './layouts/guest-layout/guest-layout.component';
import { NavbarLayoutComponent } from './layouts/navbar-layout/navbar-layout.component';
import { FullNavLayoutComponent } from './layouts/full-nav-layout/full-nav-layout.component';
import { FullNavComponent } from './shared/full-nav/full-nav.component';
import { CloListingComponent } from './clo-listing/clo-listing.component';
import { AssessmentsComponent } from './assessments/assessments.component';




import { CarouselModule } from 'primeng/carousel';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { Angular2SmartTableModule } from 'angular2-smart-table';
import { CloMappingComponent } from './clo-mapping/clo-mapping.component';
import { CloReportingComponent } from './clo-reporting/clo-reporting.component';
import { GradingComponent } from './grading/grading.component';
import { HTTP_INTERCEPTORS, HttpClient, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { AssessmentActionsComponent } from './datatable-actions/assessment-actions/assessment-actions.component';
import { CreateAssessmentComponent } from './modals/create-assessment/create-assessment.component';
import { CreateCLOComponent } from './modals/create-clo/create-clo.component';
import { SyllabusComponent } from './syllabus/syllabus.component';
import { AuthGuard } from './guard/auth.guard';
import { TokenInterceptorService } from './guard/token-interceptor.service';
import {CommonModule, NgClass, NgForOf, NgIf, NgOptimizedImage} from '@angular/common';
import { CourseCalendarComponent } from './course-calendar/course-calendar.component';

import {DndModule} from "ngx-drag-drop";
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatFormField} from '@angular/material/form-field';
import {MatOption, MatSelect} from '@angular/material/select';
import {AvatarModule, SidebarModule, SpinnerModule} from '@coreui/angular';

import {
  MatCell,

  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef,
  MatTable,

} from '@angular/material/table';
import { ButtonComponent } from './shared/button/button.component';
import { SyllabusRenderComponent } from './syllabus-render/syllabus-render.component';
import { BookManagerComponent } from './book-manager/book-manager.component';
import { TeachingMethodologyComponent } from './teaching-methodology/teaching-methodology.component';
import {MatInput} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import { StatisticsComponent } from './statistics/statistics.component';
import { CourseHeaderComponent } from './course-header/course-header.component';
import {CdkDrag, CdkDropList, CdkDropListGroup} from '@angular/cdk/drag-drop';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';
import { AdminHomeComponent } from './home/admin-home/admin-home.component';
import { HomeRouterComponent } from './home/home-router/home-router.component';
import { ChooseRoleComponent } from './home/choose-role/choose-role.component';
import { CourseOverviewComponent } from './course-overview/course-overview.component';















@NgModule({
  declarations: [
    AppComponent,
    ResetPasswordComponent,
    NavbarComponent,
    ProfHomeComponent,
    OverviewComponent,
    GuestLayoutComponent,
    NavbarLayoutComponent,
    FullNavLayoutComponent,
    FullNavComponent,
    AssessmentsComponent,
    CloListingComponent,
    CreateCLOComponent,
    SyllabusComponent,
    SyllabusRenderComponent,
    BookManagerComponent,
    TeachingMethodologyComponent,
    CourseHeaderComponent,
    CourseCalendarComponent,
    CloReportingComponent,
    CloMappingComponent,
    GradingComponent,
    AssessmentActionsComponent,
    CreateAssessmentComponent,
    LoginComponent,
    ResetPasswordRequestComponent,
    LandingPageComponent,
    UnauthorizedComponent,
    AdminHomeComponent,
    HomeRouterComponent,
    ChooseRoleComponent,
    CourseOverviewComponent,




  ],
  imports: [
    DndModule,
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    AppRoutingModule,
    CarouselModule,
    NgbModalModule,
    CommonModule,
    Angular2SmartTableModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-bottom-right',
      preventDuplicates: true,
    }),


    MatCard,
    MatCardHeader,
    MatCardTitle,
    MatFormField,
    MatSelect,
    MatOption,
    MatCardContent,
    MatTable,
    MatColumnDef,
    MatHeaderCell,
    MatHeaderCellDef,
    MatCellDef,
    MatCell,
    MatHeaderRow,
    MatRow,
    MatRowDef,
    MatHeaderRowDef,

    ButtonComponent,
    AvatarModule,
    SpinnerModule,
    SidebarModule,
    StatisticsComponent,
    MatInput,
    MatButton,
    MatButton,
    NgOptimizedImage,
    CdkDropList,
    NgForOf,
    CdkDrag,
    CdkDropListGroup,

    NgClass,
    NgIf,
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    provideHttpClient(withInterceptorsFromDi())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
