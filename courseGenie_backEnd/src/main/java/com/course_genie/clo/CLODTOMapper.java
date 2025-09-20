package com.course_genie.clo;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CLODTOMapper implements Function<CLO, CLODTO> {

    @Override
    public CLODTO apply(CLO clo) {
        return CLODTO.builder()
                .cloId(clo.getCloId())
                .name(clo.getName())
                .description(clo.getDescription())
                .courseId(clo.getCourse().getCourseId())
                .build();
    }
}
