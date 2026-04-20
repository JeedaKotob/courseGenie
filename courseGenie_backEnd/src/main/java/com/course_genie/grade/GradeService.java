package com.course_genie.grade;

import com.course_genie.enrollment.Enrollment;
import com.course_genie.enrollment.EnrollmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final GradeDTOMapper gradeDTOMapper;
    private final EnrollmentRepository enrollmentRepository;

    public GradeService(
            GradeRepository gradeRepository,
            GradeMapper gradeMapper,
            GradeDTOMapper gradeDTOMapper,
            EnrollmentRepository enrollmentRepository
    ) {
        this.gradeRepository = gradeRepository;
        this.gradeMapper = gradeMapper;
        this.gradeDTOMapper = gradeDTOMapper;
        this.enrollmentRepository = enrollmentRepository;
    }

    // Create
    public Boolean createGrade(List<GradeDTO> gradesDTO) {
        for (GradeDTO gradeDTO : gradesDTO) {
            if(gradeRepository.countGradesByAssessmentAssessmentIdAndEnrollmentEnrollmentId(gradeDTO.assessmentId(), gradeDTO.enrollmentId()) > 0) {
                Grade grade = gradeRepository.findGradeByAssessmentAssessmentIdAndEnrollmentEnrollmentId(gradeDTO.assessmentId(), gradeDTO.enrollmentId())
                        .orElseThrow(()-> new EntityNotFoundException("Grade not found"));
                grade.setScore(gradeDTO.score());
                if (gradeDTO.score() == -1) {
                    gradeRepository.delete(grade);
                } else {
                    gradeRepository.save(grade);
                }

            } else {
                if (gradeDTO.score() != -1) {
                    Grade newGrade = gradeMapper.apply(gradeDTO);
                    Enrollment enrollment = enrollmentRepository.findById(gradeDTO.enrollmentId())
                            .orElseThrow(() -> new EntityNotFoundException("Enrollment not found"));
                    newGrade.setStudentId(enrollment.getStudent().getStudentId());
                    gradeRepository.save(newGrade);
                }

            }
        }
        return Boolean.TRUE;
    }

    // Read
    public List<GradeDTO> getAllGrades() {
        return gradeRepository.findAll().stream().map(gradeDTOMapper).collect(Collectors.toList());
    }

    public GradeDTO getGradeById(long gradeId) {
        return gradeDTOMapper.apply(gradeRepository.findById(gradeId).orElseThrow(() -> new EntityNotFoundException("grade not found")));
    }

    // Update
    public GradeDTO updateGrade(GradeDTO gradeDetails) {
        if (gradeDetails.gradeId() == null) {
            throw new EntityNotFoundException("Grade id is required for update");
        }
        Grade grade = gradeRepository.findById(gradeDetails.gradeId()).orElseThrow(() -> new EntityNotFoundException("Grade not found"));
        grade.setScore(gradeDetails.score());
        return gradeDTOMapper.apply(gradeRepository.save(grade));
    }

    // Delete
    public void deleteGrade(long gradeId) {
        Grade grade = gradeRepository.findById(gradeId).orElseThrow(() -> new EntityNotFoundException("Grade not found"));
        gradeRepository.delete(grade);
    }

    public List<GradeDTO> getGradesBySection(Long sectionId) {
        return gradeRepository
                .findGradeByEnrollmentSectionSectionId(sectionId)
                .orElse(List.of())   // unwrap Optional
                .stream()           // NOW it's Stream<Grade>
                .map(gradeDTOMapper)
                .toList();
    }


}
