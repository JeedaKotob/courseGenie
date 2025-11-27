package com.course_genie.course;

import org.springframework.stereotype.Service;

import java.util.function.Function;

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
