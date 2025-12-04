import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ResetPasswordRequestComponent } from './reset-password-request/reset-password-request.component';
import { GuestLayoutComponent } from './layouts/guest-layout/guest-layout.component';
import { NavbarLayoutComponent } from './layouts/navbar-layout/navbar-layout.component';
import { FullNavLayoutComponent } from './layouts/full-nav-layout/full-nav-layout.component';
import { OverviewComponent } from './overview/overview.component';
import { AssessmentsComponent } from './assessments/assessments.component';
import { CloListingComponent } from './clo-listing/clo-listing.component';
import { CloMappingComponent } from './clo-mapping/clo-mapping.component';
import { CloReportingComponent } from './clo-reporting/clo-reporting.component';
import { GradingComponent } from './grading/grading.component';
import { SyllabusComponent } from './syllabus/syllabus.component';
import { AuthGuard } from './guard/auth.guard';
import {CourseCalendarComponent} from './course-calendar/course-calendar.component';
import {BookManagerComponent} from './book-manager/book-manager.component';
import {StatisticsComponent } from './statistics/statistics.component';
import {TeachingMethodologyComponent} from './teaching-methodology/teaching-methodology.component';
import {LandingPageComponent} from './landing-page/landing-page.component';
import {HomeRouterComponent} from './home/home-router/home-router.component';
import {ProfHomeComponent} from './home/prof-home/prof-home.component';
import {AdminHomeComponent} from './home/admin-home/admin-home.component';
import {ChooseRoleComponent} from './home/choose-role/choose-role.component';
import {RoleGuard} from './guard/role.guard';
import {CourseOverviewComponent} from './course-overview/course-overview.component';

const routes: Routes = [
  { path: '', component: LandingPageComponent, pathMatch: 'full' },
  {
    path: '' ,
    component: GuestLayoutComponent,
    children: [
      { path: 'login', component: LoginComponent },
      { path: 'reset-password-request', component: ResetPasswordRequestComponent },
      { path: 'reset-password', component: ResetPasswordComponent },
    ],
  },
  // Navbar Layout (Home)
  {
    path: '',
    component: NavbarLayoutComponent,
    children: [
      { path: 'home', component: HomeRouterComponent, canActivate: [AuthGuard] },
      { path: 'professor', component: ProfHomeComponent, canActivate: [RoleGuard], data: {roles: ['ROLE_PROFESSOR']}},
      { path: 'admin', component: AdminHomeComponent, canActivate: [RoleGuard], data: { roles: ['ROLE_ADMIN'] }},
      { path: 'choose-role', component: ChooseRoleComponent, canActivate: [RoleGuard], data: { roles: ['ROLE_ADMIN'] }},
      { path: 'admin/course/:courseCode', component: CourseOverviewComponent, canActivate: [RoleGuard], data: { roles: ['ROLE_ADMIN'] }},
    ],
  },
  {
    path: '',
    component: FullNavLayoutComponent,
    children: [
      { path: 'overview/:courseCode/:sectionCode', component: OverviewComponent, canActivate: [AuthGuard] },
      { path: 'assessments/:courseCode/:sectionCode', component: AssessmentsComponent, canActivate: [AuthGuard] },
      { path: 'clos-listing/:courseCode/:sectionCode', component: CloListingComponent, canActivate: [AuthGuard] },
      { path: 'clos-mapping/:courseCode/:sectionCode', component: CloMappingComponent, canActivate: [AuthGuard] },
      { path: 'clos-reporting/:courseCode/:sectionCode', component: CloReportingComponent, canActivate: [AuthGuard] },
      { path: 'grading/:courseCode/:sectionCode', component: GradingComponent, canActivate: [AuthGuard] },
      { path: 'syllabus/:courseCode/:sectionCode', component: SyllabusComponent, canActivate: [AuthGuard] },
      { path: 'course-calendar/:courseCode/:sectionCode', component: CourseCalendarComponent, canActivate: [AuthGuard] },
      { path: 'book-manager/:courseCode/:sectionCode', component: BookManagerComponent, canActivate: [AuthGuard] },
      { path: 'statistics/:courseCode/:sectionCode', component: StatisticsComponent, canActivate: [AuthGuard] },
      { path: 'teaching-methodology/:courseCode/:sectionCode', component: TeachingMethodologyComponent, canActivate: [AuthGuard] },

      // { path: 'admin/clo', component: CLOCrudComponent, canActivate: [AuthGuard] },
      // { path: 'admin/section', component: SectionCrudComponent, canActivate: [AuthGuard] },
      // Additional side menu routes can go here
    ],
  },
  // { path: 'login', component: LoginComponent },
  // { path: 'reset-password', component: ResetPasswordComponent },
  // { path: 'reset-password-request', component: ResetPasswordRequestComponent },
  // { path: '', redirectTo: '/login', pathMatch: 'full' },
  //
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
