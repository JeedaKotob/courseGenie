package com.course_genie.grade;

import com.course_genie.assessment.AssessmentDTOMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GradeDTOMapper implements Function<Grade, GradeDTO> {

    @Override
    public GradeDTO apply(Grade grade) {
        return GradeDTO.builder()
                .gradeId(grade.getGradeId())
                .score(grade.getScore())
                .assessmentId(grade.getAssessment().getAssessmentId())
                .enrollmentId(grade.getEnrollment().getEnrollmentId())
                .build();
    }
}
