package com.course_genie.section;

import com.course_genie.course.Course;
import com.course_genie.professor.Professor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SectionMapper implements Function<SectionDTO, Section> {
    @Override
    public Section apply(SectionDTO sectionDTO) {
        return Section.builder()
                .sectionId(sectionDTO.sectionId())
                .code(sectionDTO.code())
                .term(sectionDTO.term())
                .configured(sectionDTO.configured())
                .professor(new Professor(sectionDTO.professorId()))
                .course(new Course(sectionDTO.courseId()))
                .class_number(sectionDTO.class_number())
                .teachingMethodology(String.valueOf(Long.valueOf(sectionDTO.teachingMethodology())))
                .build();
    }
}
