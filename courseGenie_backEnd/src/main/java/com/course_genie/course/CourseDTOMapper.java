package com.course_genie.course;

import com.course_genie.assessment.Assessment;
import com.course_genie.assessment.AssessmentDTOMapper;
import com.course_genie.professor.ProfessorDTOMapper;
import com.course_genie.syllabus.SyllabusDTOMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CourseDTOMapper implements Function<Course, CourseDTO> {

    @Override
    public CourseDTO apply(Course course) {
        return CourseDTO.builder()
                .courseId(course.getCourseId())
                .code(course.getCode())
                .name(course.getName())
                .description(course.getDescription())
                .credits(course.getCredits())


                .build();
    }
}
