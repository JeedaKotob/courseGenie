package com.course_genie.section;

import com.course_genie.assessment.AssessmentDTO;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public record SectionDTO(
        long sectionId,
        String code,
        String term,
        String class_number,
        boolean configured,
        long courseId,
        long professorId,
        String teachingMethodology,
        List<AssessmentDTO> assessments
) {
    public SectionDTO {
        if (assessments == null) {
            assessments = new ArrayList<>(); // Ensure it's never null
        }
    }
}
