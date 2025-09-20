package com.course_genie.course;

import com.course_genie.assessment.Assessment;
import com.course_genie.assessment.AssessmentMapper;
import com.course_genie.professor.Professor;
import com.course_genie.professor.ProfessorMapper;
import com.course_genie.syllabus.Syllabus;
import com.course_genie.syllabus.SyllabusMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CourseMapper implements Function<CourseDTO, Course> {

    @Override
    public Course apply(CourseDTO courseDTO) {
        return Course.builder()
                .courseId(courseDTO.courseId())
                .code(courseDTO.code())
                .name(courseDTO.name())
                .description(courseDTO.description())
                .credits(courseDTO.credits())


                .build();
    }
}
