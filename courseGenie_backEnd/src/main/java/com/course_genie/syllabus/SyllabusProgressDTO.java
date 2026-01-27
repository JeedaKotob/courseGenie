package com.course_genie.syllabus;

import com.course_genie.course.CourseDTO;
import lombok.Builder;

@Builder
public record SyllabusProgressDTO(
        long professorId,
        String professorName,
        int totalSections,
        int submittedSyllabi,
        double progressPercentage
) {
}
