package com.course_genie.car;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CarDetailDTO(
        Long sectionId,
        String courseName,
        String courseCode,
        String sectionCode,
        boolean submitted,
        LocalDate submissionDate,
        LocalDate carDueDate,
        long overdueBy
) {}
