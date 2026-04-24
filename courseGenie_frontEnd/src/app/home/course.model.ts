export interface Course {
  departmentId?: number | null;
  departmentName?: string | null;
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
  semesterName: string;
  semesterId?: number | null;
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

export interface CarDetail {
  sectionId: number;
  courseName: string;
  courseCode: string;
  sectionCode: string;
  submitted: boolean;
  submissionDate: string;
  carDueDate: string;
  overdueBy: number;
}

export interface CourseCollaborator {
  professorId: number;
  professorName: string;
  professorEmail: string;
  sectionCodes: string[];
}

export interface CarProgress {
  professorId: number;
  professorName: string;
  departmentName: string;
  totalSections: number;
  submittedCars: number;
  progressPercentage: number;
  sections: CarDetail[];
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

export interface ExamSchedule {
  examScheduleId: number;
  examDate: string;
  startTime?: string;
  endTime?: string;
  semesterId: number;
  semesterName: string;
  courseId: number;
  courseCode: string;
  courseName: string;
  roomIds: number[];
  roomSeatAvailability?: RoomSeatAvailability[];
  enrolledStudentCount: number;
  assignedSeatCapacity: number;
}

export interface RoomSeatAvailability {
  roomId: number;
  remainingSeats: number;
}

export interface ExamScheduleAssignmentRequest {
  examScheduleId: number;
  roomIds: number[];
}

export interface ExamScheduleSaveRequest {
  examDate: string;
  assignments: ExamScheduleAssignmentRequest[];
}

export interface ExamCard {
  examScheduleId: number;
  courseId: number;
  courseCode: string;
  courseName: string;
  semesterName: string;
  startTime?: string;
  endTime?: string;
  roomIds: number[];
  roomSeatAvailability?: RoomSeatAvailability[];
  enrolledStudentCount: number;
  assignedSeatCapacity: number;
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
  carDueDate: string;
  overdueBy: number;
}

export interface ProfessorExamRoom {
  roomId: number;
  roomNumber: string;
  roomType: string;
  capacity: number;
  assignedCountTotal: number;
  assignedCountInSection: number;
}

export interface ProfessorExamStudent {
  enrollmentId: number;
  studentId: string;
  firstName: string;
  lastName: string;
  email: string;
  assignedRoomId: number | null;
}

export interface ProfessorExamAllocation {
  examScheduleId: number;
  examDate: string;
  startTime?: string;
  endTime?: string;
  sectionId: number;
  sectionCode: string;
  courseId: number;
  courseCode: string;
  courseName: string;
  semesterId: number;
  semesterName: string;
  enrolledStudentCount: number;
  rooms: ProfessorExamRoom[];
  students: ProfessorExamStudent[];
}

export interface StudentRoomAssignmentRequest {
  enrollmentId: number;
  roomId: number;
}

export interface SaveProfessorExamAllocationRequest {
  examScheduleId: number;
  assignments: StudentRoomAssignmentRequest[];
}

export interface ProfessorOption {
  userId: number;
  fullName: string;
  email: string;
}

export interface PeerReviewDepartmentOverview {
  departmentName: string;
  professors: ProfessorOption[];
  revieweeSections: RevieweeSectionOption[];
  assignmentCount: number;
}

export interface RevieweeSectionOption {
  sectionId: number;
  courseCode: string;
  courseName: string;
  sectionCode: string;
  revieweeId: number;
  revieweeName: string;
}

export interface PeerReviewAssignment {
  assignmentId: number;
  reviewerId: number;
  reviewerName: string;
  revieweeId: number;
  revieweeName: string;
  revieweeSectionId: number;
  courseCode: string;
  courseName: string;
  sectionCode: string;
  departmentName: string;
  pairingSource: string;
  progressStatus: 'NOT_STARTED' | 'REVIEWER_FINISHED' | 'DONE';
}

export interface PeerReviewPairRequest {
  reviewerId: number;
  revieweeSectionId: number;
}

export interface ReviewerAssignment {
  assignmentId: number;
  reviewerId: number;
  reviewerName: string;
  revieweeId: number;
  revieweeName: string;
  revieweeSectionId: number;
  courseCode: string;
  courseName: string;
  sectionCode: string;
  departmentName: string;
  completed: boolean;
}

export interface ReviewerSubmitPeerReviewRequest {
  assignmentId: number;
  reviewerId: number;
  alignmentScore: number | null;
  alignmentComment: string;
  assessmentDesignScore: number | null;
  assessmentDesignComment: string;
  gradingClarityScore: number | null;
  gradingClarityComment: string;
  feedbackEfficiencyScore: number | null;
  feedbackEfficiencyComment: string;
  courseGradeDistributionNote: string;
  courseReflectionNote: string;
  innovationJourneyNote: string;
  otherNote: string;
  summary: string;
}

export interface RevieweeReceivedReview {
  peerReviewId: number;
  revieweeId: number;
  reviewerName: string;
  courseCode: string;
  courseName: string;
  sectionCode: string;
  alignmentScore: number | null;
  alignmentComment: string;
  assessmentDesignScore: number | null;
  assessmentDesignComment: string;
  gradingClarityScore: number | null;
  gradingClarityComment: string;
  feedbackEfficiencyScore: number | null;
  feedbackEfficiencyComment: string;
  courseGradeDistributionNote: string;
  courseReflectionNote: string;
  innovationJourneyNote: string;
  otherNote: string;
  summary: string;
  submittedAt: string;
  actionPlan: string;
  reflectionSubmitted: boolean;
  reflectionSubmittedAt: string | null;
}

export interface PeerReviewPublishStatus {
  globallyVisible: boolean;
  unassignedDepartments: string[];
}

export interface PeerReviewProfessorVisibility {
  visible: boolean;
  departmentAssigned: boolean;
  warning: string;
}
