package com.course_genie.assessment;

import com.course_genie.clo.CLO;
import com.course_genie.clo.CLOMapper;
import com.course_genie.section.Section;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AssessmentMapper implements Function<AssessmentDTO, Assessment> {

    private final CLOMapper cloMapper;

    public AssessmentMapper(CLOMapper cloMapper) {
        this.cloMapper = cloMapper;
    }

    @Override
    public Assessment apply(AssessmentDTO assessmentDTO) {
        return Assessment.builder()
                .assessmentId(assessmentDTO.assessmentId())
                .name(assessmentDTO.name())
                .categoryName(assessmentDTO.category())
                .shortName(assessmentDTO.shortName())
                .maxPoints(assessmentDTO.maxPoints())
                .week(assessmentDTO.week())
                .section(new Section(assessmentDTO.sectionId()))
                .clos(assessmentDTO.clos() == null ? new ArrayList<>() : assessmentDTO.clos().stream().map(cloMapper).collect(Collectors.toList()))
                .build();
    }
}
