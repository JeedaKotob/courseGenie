package com.course_genie.section;

import com.course_genie.assessment.Assessment;
import com.course_genie.assessment.AssessmentRepository;
import com.course_genie.enrollment.EnrollmentRepository;
import com.course_genie.grade.Grade;
import com.course_genie.grade.GradeRepository;
import com.course_genie.semester.Semester;
import com.course_genie.semester.SemesterRepository;
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
    private final SemesterRepository semesterRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final GradeRepository gradeRepository;

    public SectionService(SectionRepository sectionRepository, AssessmentRepository assessmentRepository, SectionDTOMapper sectionDTOMapper, SectionMapper sectionMapper, EnrollmentService enrollmentService, SemesterRepository semesterRepository, EnrollmentRepository enrollmentRepository, GradeRepository gradeRepository) {
        this.sectionRepository = sectionRepository;
        this.assessmentRepository = assessmentRepository;
        this.sectionDTOMapper = sectionDTOMapper;
        this.sectionMapper = sectionMapper;
        this.enrollmentService = enrollmentService;
        this.semesterRepository = semesterRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.gradeRepository = gradeRepository;
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
    public List<String> getSemesterNames() {
        return semesterRepository.findAllByOrderBySemesterNameDesc()
                .stream()
                .map(Semester::getSemesterName)
                .toList();
    }

    public Double calculateAverageGpa(Long sectionId) {
        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentBySectionSectionId(sectionId);
        List<Grade> grades = gradeRepository.findGradeByEnrollmentSectionSectionId(sectionId)
                .orElse(new ArrayList<>());

        if (enrollments.isEmpty()) {
            return 0.0;
        }

        var studentTotals = new java.util.HashMap<Long, Double>();
        for (Grade grade : grades) {
            studentTotals.merge(grade.getEnrollment().getEnrollmentId(), grade.getScore(), Double::sum);
        }

        double points = 0.0;
        int gradedStudents = 0;

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStatus() == Enrollment.EnrollmentStatus.WITHDRAWN) {
                continue;
            }

            double totalScore = studentTotals.getOrDefault(enrollment.getEnrollmentId(), 0.0);
            String letter = convertToLetter(totalScore);
            points += convertLetterToPoints(letter);
            gradedStudents++;
        }

        return gradedStudents == 0 ? 0.0 : points / gradedStudents;
    }

    private String convertToLetter(double score) {
        if (score >= 94) return "A";
        if (score >= 90) return "A-";
        if (score >= 87) return "B+";
        if (score >= 83) return "B";
        if (score >= 80) return "B-";
        if (score >= 77) return "C+";
        if (score >= 73) return "C";
        if (score >= 70) return "C-";
        if (score >= 60) return "D";
        return "F";
    }

    private double convertLetterToPoints(String letter) {
        return switch (letter) {
            case "A" -> 4.0;
            case "A-" -> 3.67;
            case "B+" -> 3.33;
            case "B" -> 3.0;
            case "B-" -> 2.67;
            case "C+" -> 2.33;
            case "C" -> 2.0;
            case "C-" -> 1.67;
            case "D" -> 1.0;
            default -> 0.0;
        };
    }
}
