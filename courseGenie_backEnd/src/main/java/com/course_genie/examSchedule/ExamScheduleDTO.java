package com.course_genie.examSchedule;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
public record ExamScheduleDTO(
        long examScheduleId,
        LocalDate examDate,
        LocalTime startTime,
        LocalTime endTime,
        long semesterId,
        String semesterName,
        long courseId,
        String courseCode,
        String courseName,
        List<Long> roomIds,
        List<RoomSeatAvailabilityDTO> roomSeatAvailability,
        long enrolledStudentCount,
        int assignedSeatCapacity
) {
}
