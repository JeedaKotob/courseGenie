package com.course_genie.section;

import com.course_genie.assessment.Assessment;
import com.course_genie.assessment.AssessmentRepository;
import com.course_genie.student.StudentDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.course_genie.enrollment.Enrollment;
import com.course_genie.enrollment.EnrollmentService;
import com.course_genie.student.Student;


import java.util.ArrayList;
import java.util.List;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final AssessmentRepository assessmentRepository;
    private final SectionDTOMapper sectionDTOMapper;
    private final SectionMapper sectionMapper;
    private final EnrollmentService enrollmentService;

    public SectionService(SectionRepository sectionRepository, AssessmentRepository assessmentRepository, SectionDTOMapper sectionDTOMapper, SectionMapper sectionMapper, EnrollmentService enrollmentService) {
        this.sectionRepository = sectionRepository;
        this.assessmentRepository = assessmentRepository;
        this.sectionDTOMapper = sectionDTOMapper;
        this.sectionMapper = sectionMapper;
        this.enrollmentService = enrollmentService;
    }

    public Boolean saveConfiguration(Long sectionId) {
        Section sectionToReplicate = sectionRepository.findById(sectionId).orElseThrow(() -> new EntityNotFoundException("Section not found"));
        sectionToReplicate.setConfigured(Boolean.TRUE);
        sectionRepository.save(sectionToReplicate);
        List<Section> sections= sectionRepository.findSectionByCourseCourseIdAndProfessorUserIdAndConfigured(sectionToReplicate.getCourse().getCourseId(), sectionToReplicate.getProfessor().getUserId(), Boolean.FALSE).orElse(new ArrayList<>());
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

//    public List<Student> getStudentsBySection(Long sectionId) {
//
//        sectionRepository.findById(sectionId)
//                .orElseThrow(() -> new EntityNotFoundException("Section not found"));
//
//        return enrollmentService.getEnrollmentsBySection(sectionId)
//                .stream()
//                .map(Enrollment::getStudent)
//                .toList();
//    }


    public SectionDTO getSectionById(Long sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Section not found"));
        return sectionDTOMapper.apply(section);
    }

}
