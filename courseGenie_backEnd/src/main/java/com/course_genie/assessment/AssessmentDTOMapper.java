package com.course_genie.assessment;

import com.course_genie.clo.CLO;
import com.course_genie.clo.CLODTOMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AssessmentDTOMapper implements Function<Assessment, AssessmentDTO> {

    private final CLODTOMapper cloDTOMapper;

    public AssessmentDTOMapper(CLODTOMapper cloDTOMapper) {
        this.cloDTOMapper = cloDTOMapper;
    }

    @Override
    public AssessmentDTO apply(Assessment assessment) {
        return AssessmentDTO.builder()
                .assessmentId(assessment.getAssessmentId())
                .name(assessment.getName())
                .category(assessment.getCategoryName())
                .shortName(assessment.getShortName())
                .maxPoints(assessment.getMaxPoints())
                .week(assessment.getWeek())
                .sectionId(assessment.getSection().getSectionId())
                .clos(assessment.getClos().stream().map(cloDTOMapper).collect(Collectors.toSet()))
                .build();
    }
}
