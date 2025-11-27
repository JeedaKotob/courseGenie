package com.course_genie.course;

import org.springframework.stereotype.Service;

import java.util.function.Function;

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
