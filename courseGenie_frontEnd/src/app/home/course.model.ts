export interface Course {
  discipline: String;
  courseId: number;
  code: string;
  name: string;
  description: string;
  credits: string;
  semester: string;
  sections: Section[];
  clos: CLO[];
}

export interface Section {
  sectionId: number;
  code: string;
  configured: boolean;
  professorId: number;
  professorName?: string;
  assessments: Assessment[];
  assessmentCount: number;
  term: string;
}

export interface Assessment {
  assessmentId: number;
  name: string;
  category: string;
  shortName: string;
  maxPoints: number;
  sectionId: number;
  week: number;
  clos: CLO[];
}

export interface CLO {
  cloId: number;
  name: string;
  description: string;
}

export interface CoursesBySemester {
  [semester: string]: Course[];
}

export interface NavigationState {
  course: Course
}

export interface Student {
  studentId: string;
  firstName: string;
  lastName: string;
  email: string;
}

export interface Grade {
  gradeId: number;
  score: number;
  assessmentId: number;
  enrollmentId: number;
}

export interface Syllabus {
  syllabusId: number;
  content: string;
  sectionId: number;
  submitted: boolean;
  submissionDate: string;
  syllabusDueDate: string;
  overdueBy: number;
}

export interface SyllabusDetail {
  courseName: string;
  courseCode: string;
  sectionCode: string;
  submitted: boolean;
  submissionDate: string;
  syllabusDueDate: string;
  overdueBy: number;
}

export interface SyllabusProgress {
  professorId: number;
  professorName: string;
  departmentName: string;
  totalSections: number;
  submittedSyllabi: number;
  progressPercentage: number;
  sections: SyllabusDetail[];
}


export interface User {
  userId: number;
  firstName: string;
  lastName: string;
  userName: string;
  email: string;
  jwtToken: string;
  roles: string[];
}

export interface Benchmark {
  benchmarkId: number;
  benchmarkType: string;
  description: string;
  threshold: number;
  percentage: number;
}

export interface Enrollment {
  enrollmentId: number;
  studentId: string;
  firstName: string;
  lastName: string;
  email: string;
  sectionId: number;
}

export enum RoomType {
  CLASSROOM = 'CLASSROOM',
  LAB = 'LAB'
}

export interface ExamRoom {
  roomId: number;
  capacity: number;
  roomNumber: string;
  roomType: RoomType;
}

export interface CalendarEvent {
  title: string;
  date: string;
  startTime: string;
  endTime: string;
  room: string;
  type: string;
}

export interface CalendarDay {
  date: Date;
  isoDate: string;
  inCurrentMonth: boolean;
  isToday: boolean;
  events: CalendarEvent[];
}

export interface CloResultDTO {
  cloId: number;
  name: string;
  description: string;
  assessmentMethods: string;
  benchmarkThreshold: number;
  actualResult: number;
  met: boolean;
}

export interface Car {
  carId: number;
  sectionId: number;
  courseCode: string;
  courseTitle: string;
  enrollment: number;
  withdrawals: number;
  classGpa: number;
  designatedInnovationJourneyCourse: boolean;
  gradeDistribution: { [key: string]: number };
  cloResults: CloResultDTO[];
  studentFeedbackSynopsis: string;
  impedimentsAnalysis: string;
  suggestedModifications: string;
  aiReflection: string;
  submitted: boolean;
  submissionDate: string;
  isComplete: boolean;
}
