// menuItems.ts
export interface Submenu {
  label: string;
  icon: string;
  path: string;
  roles?: string[]; // Add roles to submenus
}

export interface MenuItem {
  label: string;
  path?: string;
  icon?: string;
  submenu?: Submenu[];
  roles?: string[]; // Add roles field
}

export const menuItems: MenuItem[] = [
  {
    label: 'Home',
    path: '/home',
    icon: 'pi pi-home',
    // Everyone can access home
  },
  {
    label: 'Overview',
    path: '/overview/:courseCode/:sectionCode',
    icon: 'pi pi-search',
    roles: ['ROLE_PROFESSOR', 'ROLE_ADMIN'],
  },
  {
    label: 'Assessments',
    path: '/assessments/:courseCode/:sectionCode',
    icon: 'pi pi-file-check',
    roles: ['ROLE_PROFESSOR'],
  },
  {
    label: 'Course Calendar',
    path: '/course-calendar/:courseCode/:sectionCode',
    icon: 'pi pi-calendar',
    roles: ['ROLE_PROFESSOR', 'ROLE_ADMIN'],
  },
  {
    label: 'CLOs Listing',
    path: '/clos-listing/:courseCode/:sectionCode',
    icon: 'pi pi-list',
    roles: ['ROLE_PROFESSOR'],
  },
  {
    label: 'CLO Mapping',
    path: '/clos-mapping/:courseCode/:sectionCode',
    icon: 'pi pi-sitemap',
    roles: ['ROLE_PROFESSOR'],
  },
  {
    label: 'CLO Reporting',
    path: '/clos-reporting/:courseCode/:sectionCode',
    icon: 'pi pi-chart-bar',
    roles: ['ROLE_PROFESSOR', 'ROLE_ADMIN'],
  },
  {
    label: 'Grading',
    path: '/grading/:courseCode/:sectionCode',
    icon: 'pi pi-graduation-cap',
    roles: ['ROLE_PROFESSOR'],
  },
  {
    label: 'Statistics',
    path: '/statistics/:courseCode/:sectionCode',
    icon: 'pi pi-chart-line',
    roles: ['ROLE_PROFESSOR', 'ROLE_ADMIN'],
  },
  {
    label: 'Syllabus',
    path: '/syllabus/:courseCode/:sectionCode',
    icon: 'pi pi-folder',
    roles: ['ROLE_PROFESSOR'],
  },
  {
    label: 'Books',
    path: '/book-manager/:courseCode/:sectionCode',
    icon: 'pi pi-book',
    roles: ['ROLE_PROFESSOR'],
  },
  {
    label: 'Teaching Methodology',
    path: '/teaching-methodology/:courseCode/:sectionCode',
    icon: 'pi pi-briefcase',
    roles: ['ROLE_PROFESSOR'],
  },
  // Admin-only menu item example
  // {
  //   label: 'Courses',
  //   path: '/admin/course',
  //   icon: 'pi pi-cog',
  //   roles: ['ROLE_ADMIN'],
  // }
];
