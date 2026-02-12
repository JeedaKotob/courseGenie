package com.course_genie.section;

import com.course_genie.assessment.AssessmentRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SectionDTOMapper implements Function<Section, SectionDTO> {
    private final AssessmentRepository assessmentRepository;
    public SectionDTOMapper(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }
    @Override
    public SectionDTO apply(Section section) {
        long count = assessmentRepository.countBySectionSectionId(section.getSectionId());
        return SectionDTO.builder()
                .sectionId(section.getSectionId())
                .code(section.getCode())
                .term(section.getTerm())
                .configured(section.isConfigured())
                .professorId(section.getProfessor().getUserId())
                .professorName(section.getProfessor().getFullName())
                .courseId(section.getCourse().getCourseId())
                .courseCode(section.getCourse().getCode())
                .courseName(section.getCourse().getName())
                .class_number(section.getClass_number())
                .teachingMethodology(String.valueOf(section.getTeachingMethodology()))
                .assessmentCount(count)
                .build();
    }
}
