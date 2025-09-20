package com.course_genie.section;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SectionDTOMapper implements Function<Section, SectionDTO> {
    @Override
    public SectionDTO apply(Section section) {
        return SectionDTO.builder()
                .sectionId(section.getSectionId())
                .code(section.getCode())
                .term(section.getTerm())
                .configured(section.isConfigured())
                .professorId(section.getProfessor().getProfessorId())
                .courseId(section.getCourse().getCourseId())
                .class_number(section.getClass_number())
                .teachingMethodology(String.valueOf(section.getTeachingMethodology()))
                .build();
    }
}
