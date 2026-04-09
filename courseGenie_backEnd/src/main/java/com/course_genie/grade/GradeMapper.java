package com.course_genie.grade;

import com.course_genie.assessment.Assessment;
import com.course_genie.enrollment.Enrollment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GradeMapper implements Function<GradeDTO, Grade> {

    @Override
    public Grade apply(GradeDTO gradeDTO) {
        long gradeId = gradeDTO.gradeId() == null ? 0L : gradeDTO.gradeId();
        return Grade.builder()
                .gradeId(gradeId)
                .score(gradeDTO.score())
                .assessment(new Assessment(gradeDTO.assessmentId()))
                .enrollment(new Enrollment(gradeDTO.enrollmentId()))
                .build();
    }
}
