package com.course_genie.syllabus;

import com.course_genie.course.CourseDTOMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SyllabusDTOMapper implements Function<Syllabus, SyllabusDTO> {

    private final CourseDTOMapper courseDTOMapper;

    public SyllabusDTOMapper(CourseDTOMapper courseDTOMapper) {
        this.courseDTOMapper = courseDTOMapper;
    }

    @Override
    public SyllabusDTO apply(Syllabus syllabus) {
        return SyllabusDTO.builder()
                .syllabusId(syllabus.getSyllabusId())
                .content(syllabus.getContent())
                .sectionId(syllabus.getSection().getSectionId())
                .build();
    }
}
