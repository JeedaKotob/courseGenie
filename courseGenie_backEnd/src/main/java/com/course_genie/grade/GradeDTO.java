package com.course_genie.grade;

import lombok.Builder;

@Builder
public record GradeDTO(
        Long gradeId,
        double score,
        long assessmentId,
        long enrollmentId
) {
}
