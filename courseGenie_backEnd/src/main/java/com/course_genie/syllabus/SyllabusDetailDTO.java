package com.course_genie.syllabus;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SyllabusDetailDTO(
        Long sectionId,
        String courseName,
        String courseCode,
        String sectionCode,
        boolean submitted,
        LocalDate submissionDate
){}
