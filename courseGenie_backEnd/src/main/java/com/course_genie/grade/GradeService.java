package com.course_genie.grade;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final GradeDTOMapper gradeDTOMapper;

    public GradeService(GradeRepository gradeRepository, GradeMapper gradeMapper, GradeDTOMapper gradeDTOMapper) {
        this.gradeRepository = gradeRepository;
        this.gradeMapper = gradeMapper;
        this.gradeDTOMapper = gradeDTOMapper;
    }

    // Create
    public Boolean createGrade(List<GradeDTO> gradesDTO) {
        for (GradeDTO gradeDTO : gradesDTO) {
            if(gradeRepository.countGradesByAssessmentAssessmentIdAndStudentStudentId(gradeDTO.assessmentId(), gradeDTO.studentId()) > 0) {
                Grade grade = gradeRepository.findGradeByAssessmentAssessmentIdAndStudentStudentId(gradeDTO.assessmentId(), gradeDTO.studentId())
                        .orElseThrow(()-> new EntityNotFoundException("Grade not found"));
                grade.setScore(gradeDTO.score());
                if (gradeDTO.score() == -1) {
                    gradeRepository.delete(grade);
                } else {
                    gradeRepository.save(grade);
                }

            } else {
                if (gradeDTO.score() != -1) {
                    gradeRepository.save(gradeMapper.apply(gradeDTO));
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
        Grade grade = gradeRepository.findById(gradeDetails.gradeId()).orElseThrow(() -> new EntityNotFoundException("Grade not found"));
        grade.setScore(gradeDetails.score());
        return gradeDTOMapper.apply(gradeRepository.save(grade));
    }

    // Delete
    public void deleteGrade(long gradeId) {
        Grade grade = gradeRepository.findById(gradeId).orElseThrow(() -> new EntityNotFoundException("Grade not found"));
        gradeRepository.delete(grade);
    }
}
