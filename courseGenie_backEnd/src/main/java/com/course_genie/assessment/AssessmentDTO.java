package com.course_genie.assessment;

import com.course_genie.clo.CLODTO;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record AssessmentDTO(
        long assessmentId,
        String name,
        String category,
        String shortName,
        int maxPoints,
        int week,
        long sectionId,
        Set<CLODTO> clos
) {
}
