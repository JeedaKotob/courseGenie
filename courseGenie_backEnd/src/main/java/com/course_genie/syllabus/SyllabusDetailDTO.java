package com.course_genie.syllabus;

import lombok.Builder;

@Builder
public record SyllabusDetailDTO(
        Long sectionId,
        String courseName,
        String courseCode,
        String sectionCode,
        boolean submitted
){}
