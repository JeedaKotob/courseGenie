package com.course_genie.grade;

import com.course_genie.assessment.AssessmentDTO;
import lombok.Builder;

@Builder
public record GradeDTO(
        long gradeId,
        double score,
        long assessmentId,
        long enrollmentId
) {
}
