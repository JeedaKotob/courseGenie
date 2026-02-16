package com.course_genie.grade;

import com.course_genie.assessment.Assessment;
import com.course_genie.assessment.AssessmentMapper;
import com.course_genie.enrollment.Enrollment;
import com.course_genie.student.Student;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GradeMapper implements Function<GradeDTO, Grade> {

    @Override
    public Grade apply(GradeDTO gradeDTO) {
        return Grade.builder()
                .gradeId(gradeDTO.gradeId())
                .score(gradeDTO.score())
                .assessment(new Assessment(gradeDTO.assessmentId()))
                .enrollment(new Enrollment(gradeDTO.enrollmentId()))
                .build();
    }
}
