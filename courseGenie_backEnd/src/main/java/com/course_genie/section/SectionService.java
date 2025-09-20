package com.course_genie.section;

import com.course_genie.assessment.Assessment;
import com.course_genie.assessment.AssessmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final AssessmentRepository assessmentRepository;
    private final SectionDTOMapper sectionDTOMapper;
    private final SectionMapper sectionMapper;

    public SectionService(SectionRepository sectionRepository, AssessmentRepository assessmentRepository, SectionDTOMapper sectionDTOMapper, SectionMapper sectionMapper) {
        this.sectionRepository = sectionRepository;
        this.assessmentRepository = assessmentRepository;
        this.sectionDTOMapper = sectionDTOMapper;
        this.sectionMapper = sectionMapper;
    }

    public Boolean saveConfiguration(Long sectionId) {
        Section sectionToReplicate = sectionRepository.findById(sectionId).orElseThrow(() -> new EntityNotFoundException("Section not found"));
        sectionToReplicate.setConfigured(Boolean.TRUE);
        sectionRepository.save(sectionToReplicate);
        List<Section> sections= sectionRepository.findSectionByCourseCourseIdAndProfessorProfessorIdAndConfigured(sectionToReplicate.getCourse().getCourseId(), sectionToReplicate.getProfessor().getProfessorId(), Boolean.FALSE).orElse(new ArrayList<>());
        List<Assessment> assessmentsToReplicate = assessmentRepository.findAssessmentBySectionSectionId(sectionToReplicate.getSectionId()).orElse(new ArrayList<>());
        List<Assessment> assessmentsReplicat = new ArrayList<>();
        for (Section section : sections) {
            for (Assessment assessment : assessmentsToReplicate) {
                assessmentsReplicat.add(new Assessment(assessment.getName(), assessment.getCategoryName(), assessment.getShortName(), assessment.getMaxPoints(), assessment.getSection(),assessment.getWeek(), new ArrayList<>(assessment.getClos())));
            }
            section.setConfigured(Boolean.TRUE);
        }
        sectionRepository.saveAll(sections);
        assessmentRepository.saveAll(assessmentsReplicat);

        return true;
    }

    public String getTeachingMethodology(Long sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Section not found with id: " + sectionId));
        return section.getTeachingMethodology();
    }

    public void setTeachingMethodology(Long sectionId, String methodologyText) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Section not found with id: " + sectionId));
        section.setTeachingMethodology(String.valueOf((methodologyText)));
        sectionRepository.save(section);
    }
}
