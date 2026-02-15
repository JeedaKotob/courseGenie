package com.course_genie.syllabus;

import com.course_genie.course.CourseDTO;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SyllabusDTO(
        long syllabusId,
        String content,
        long sectionId,
        boolean submitted
) {
}
