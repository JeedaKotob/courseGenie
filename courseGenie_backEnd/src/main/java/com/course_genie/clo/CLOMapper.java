package com.course_genie.clo;

import com.course_genie.course.Course;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CLOMapper implements Function<CLODTO, CLO> {
    @Override
    public CLO apply(CLODTO clodto) {
        return CLO.builder()
                .cloId(clodto.cloId())
                .name(clodto.name())
                .description(clodto.description())
                .course(new Course(clodto.courseId()))
                .build();
    }
}
