package com.course_genie.sectionExamAllocation;

public record ProfessorExamStudentDTO(
        long enrollmentId,
        String studentId,
        String firstName,
        String lastName,
        String email,
        Long assignedRoomId
) {
}
