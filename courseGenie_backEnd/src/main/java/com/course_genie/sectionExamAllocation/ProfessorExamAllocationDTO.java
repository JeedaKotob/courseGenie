package com.course_genie.sectionExamAllocation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ProfessorExamAllocationDTO(
        long examScheduleId,
        LocalDate examDate,
        LocalTime startTime,
        LocalTime endTime,
        long sectionId,
        String sectionCode,
        long courseId,
        String courseCode,
        String courseName,
        long semesterId,
        String semesterName,
        int enrolledStudentCount,
        List<ProfessorExamRoomDTO> rooms,
        List<ProfessorExamStudentDTO> students
) {
}
