package com.course_genie.examSchedule;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ExamScheduleDTO(
        long examScheduleId,
        LocalDate examDate,
        String timeSlot,
        long semesterId,
        String semesterName,
        long courseId,
        String courseCode,
        String courseName,
        List<Long> roomIds
) {
}
