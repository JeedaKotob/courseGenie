package com.course_genie.assessment;

import com.course_genie.assessment.CategoryDescription;

import com.course_genie.clo.CLO;
import com.course_genie.clo.CLORepository;
import com.course_genie.section.Section;
import com.course_genie.section.SectionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing assessments.
 */
@Service
public class AssessmentService {
    private final AssessmentRepository assessmentRepository;
    private final CLORepository cloRepository;
    private final AssessmentDTOMapper assessmentDTOMapper;
    private final AssessmentMapper assessmentMapper;
    private final CategoryDescriptionRepository categoryDescriptionRepository;
    private final SectionRepository sectionRepository;

    /**
     * Constructs an AssessmentService with the given repositories and mappers.
     *
     * @param assessmentRepository the repository for assessments
     * @param cloRepository        the repository for CLOs
     * @param assessmentDTOMapper  the mapper for converting Assessment to AssessmentDTO
     * @param assessmentMapper     the mapper for converting AssessmentDTO to Assessment
     */
    public AssessmentService(AssessmentRepository assessmentRepository, CLORepository cloRepository,
                             AssessmentDTOMapper assessmentDTOMapper, AssessmentMapper assessmentMapper,
                             CategoryDescriptionRepository categoryDescriptionRepository,
                             SectionRepository sectionRepository) {
        this.assessmentRepository = assessmentRepository;
        this.cloRepository = cloRepository;
        this.assessmentDTOMapper = assessmentDTOMapper;
        this.assessmentMapper = assessmentMapper;
        this.categoryDescriptionRepository = categoryDescriptionRepository;
        this.sectionRepository = sectionRepository;
    }

    public AssessmentDTO createAssessment(AssessmentDTO assessmentDTO) {
        Assessment assessment = assessmentMapper.apply(assessmentDTO);
        return assessmentDTOMapper.apply(assessmentRepository.save(assessment));
    }

    public List<AssessmentDTO> getAllAssessments() {
        return assessmentRepository.findAll()
                .stream()
                .map(assessmentDTOMapper)
                .collect(Collectors.toList());
    }

    public AssessmentDTO getAssessmentById(long id) {
        Assessment assessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assessment not found"));
        return assessmentDTOMapper.apply(assessment);
    }

    public AssessmentDTO updateAssessment(AssessmentDTO assessmentDTO) {
        Assessment assessment = assessmentRepository.findById(assessmentDTO.assessmentId())
                .orElseThrow(() -> new EntityNotFoundException("Assessment not found"));
        assessment.setName(assessmentDTO.name());
        assessment.setCategoryName(assessmentDTO.category());
        assessment.setShortName(assessmentDTO.shortName());
        assessment.setMaxPoints(assessmentDTO.maxPoints());
        return assessmentDTOMapper.apply(assessmentRepository.save(assessment));
    }

    public void deleteAssessment(long id) {
        assessmentRepository.deleteById(id);
    }

    public Boolean mapCloToAssessment(long assessmentId, long cloId) {
        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new EntityNotFoundException("Assessment not found"));
        CLO clo = cloRepository.findById(cloId)
                .orElseThrow(() -> new EntityNotFoundException("CLO not found"));

        assessment.getClos().add(clo);
        assessmentRepository.save(assessment);
        return Boolean.TRUE;
    }

    public Boolean unmapCloToAssessment(long assessmentId, long cloId) {
        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new EntityNotFoundException("Assessment not found"));
        CLO clo = cloRepository.findById(cloId)
                .orElseThrow(() -> new EntityNotFoundException("CLO not found"));

        assessment.getClos().remove(clo);
        assessmentRepository.save(assessment);
        return Boolean.TRUE;
    }

    public AssessmentDTO updateAssessmentWeek(long assessmentId, int week) {
        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new EntityNotFoundException("Assessment not found"));
        assessment.setWeek(week);
        return assessmentDTOMapper.apply(assessmentRepository.save(assessment));
    }

    public void createCategoryDescription(Long sectionId, String categoryName, String description) {
        Section section = sectionRepository.findById(sectionId)
            .orElseThrow(() -> new RuntimeException("Section not found with ID: " + sectionId));

        CategoryDescription cd = categoryDescriptionRepository
            .findFirstBySection_SectionIdAndCategoryNameIgnoreCase(sectionId, categoryName)
            .orElseGet(() -> {
                CategoryDescription newCd = new CategoryDescription();
                newCd.setSection(section);
                newCd.setCategoryName(categoryName);
                return newCd;
            });

        cd.setDescription(description);
        categoryDescriptionRepository.save(cd);
    }

    public String getCategoryDescription(Long sectionId, String categoryName) {
        return categoryDescriptionRepository
            .findFirstBySection_SectionIdAndCategoryNameIgnoreCase(sectionId, categoryName)
            .map(CategoryDescription::getDescription)
            .orElse("No description found.");
    }

    public void updateCategoryDescription(Long sectionId, String categoryName, String newDescription) {
        CategoryDescription cd = categoryDescriptionRepository
            .findFirstBySection_SectionIdAndCategoryNameIgnoreCase(sectionId, categoryName)
            .orElseThrow(() -> new EntityNotFoundException("Category description not found."));
        cd.setDescription(newDescription);
        categoryDescriptionRepository.save(cd);
    }

    public void deleteCategoryDescription(Long sectionId, String categoryName) {
        CategoryDescription cd = categoryDescriptionRepository
            .findFirstBySection_SectionIdAndCategoryNameIgnoreCase(sectionId, categoryName)
            .orElseThrow(() -> new EntityNotFoundException("Category description not found."));
        categoryDescriptionRepository.delete(cd);
    }

    public List<AssessmentDTO> getAssessmentsByCourseAndSection(String courseCode, String sectionCode) {
        return assessmentRepository
                .findAssessmentBySectionSectionId(
                        sectionRepository.findSectionByCodeAndCourseCode(sectionCode, courseCode)
                                .orElseThrow(() -> new EntityNotFoundException("Section not found"))
                                .getSectionId()
                )
                .orElse(List.of())
                .stream()
                .map(assessmentDTOMapper)
                .toList();
    }

}