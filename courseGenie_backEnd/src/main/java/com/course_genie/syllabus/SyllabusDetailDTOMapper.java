package com.course_genie.syllabus;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SyllabusDetailDTOMapper implements Function<Syllabus, SyllabusDetailDTO> {

    @Override
    public SyllabusDetailDTO apply(Syllabus syllabus) {
        return SyllabusDetailDTO.builder()
                .sectionId(syllabus.getSection().getSectionId())
                .courseName(syllabus.getSection().getCourse().getName())
                .courseCode(syllabus.getSection().getCourse().getCode())
                .sectionCode(syllabus.getSection().getCode())
                .submitted(syllabus.isSubmitted())
                .build();
    }
}
