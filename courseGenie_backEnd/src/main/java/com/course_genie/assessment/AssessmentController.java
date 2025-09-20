package com.course_genie.assessment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @PostMapping
    public ResponseEntity<AssessmentDTO> createAssessment(@RequestBody AssessmentDTO assessmentDTO) {
        AssessmentDTO createdAssessment = assessmentService.createAssessment(assessmentDTO);
        return ResponseEntity.ok(createdAssessment);
    }

    @GetMapping
    public ResponseEntity<List<AssessmentDTO>> getAllAssessments() {
        List<AssessmentDTO> assessments = assessmentService.getAllAssessments();
        return ResponseEntity.ok(assessments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssessmentDTO> getAssessmentById(@PathVariable long id) {
        AssessmentDTO assessment = assessmentService.getAssessmentById(id);
        return ResponseEntity.ok(assessment);
    }

    @PutMapping
    public ResponseEntity<AssessmentDTO> updateAssessment(@RequestBody AssessmentDTO assessmentDTO) {
        AssessmentDTO updatedAssessment = assessmentService.updateAssessment(assessmentDTO);
        return ResponseEntity.ok(updatedAssessment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssessment(@PathVariable long id) {
        assessmentService.deleteAssessment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{assassmentId}/clo/{cloId}")
    public ResponseEntity<Boolean> mapCloToAssessment(@PathVariable long assassmentId, @PathVariable long cloId) {
        return ResponseEntity.ok(assessmentService.mapCloToAssessment(assassmentId, cloId));
    }

    @PutMapping("/{assassmentId}/clo/{cloId}")
    public ResponseEntity<Boolean> unmapCloToAssessment(@PathVariable long assassmentId, @PathVariable long cloId) {
        return ResponseEntity.ok(assessmentService.unmapCloToAssessment(assassmentId, cloId));
    }

    @PutMapping("/{assessmentId}/week")
    public ResponseEntity<AssessmentDTO> updateAssessmentWeek(@PathVariable long assessmentId,
                                                              @RequestBody Assessment assessment) {
        // Call the service method to update the week for the given assessment ID
        AssessmentDTO updatedAssessment = assessmentService.updateAssessmentWeek(assessmentId, assessment.getWeek());

        // Return the updated assessment in the response
        return ResponseEntity.ok(updatedAssessment);
    }

    @PostMapping("/{sectionId}/category-description/{categoryName}")
    public ResponseEntity<Void> createCategoryDescription(@PathVariable Long sectionId,
                                                          @PathVariable String categoryName,
                                                          @RequestParam String description) {
        assessmentService.createCategoryDescription(sectionId, categoryName, description);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{sectionId}/category-description/{categoryName}")
    public ResponseEntity<String> getCategoryDescription(@PathVariable Long sectionId,
                                                         @PathVariable String categoryName) {
        String description = assessmentService.getCategoryDescription(sectionId, categoryName);
        return ResponseEntity.ok(description);
    }

    @PutMapping("/{sectionId}/category-description/{categoryName}")
    public ResponseEntity<Void> updateCategoryDescription(@PathVariable Long sectionId,
                                                          @PathVariable String categoryName,
                                                          @RequestParam String description) {
        assessmentService.updateCategoryDescription(sectionId, categoryName, description);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{sectionId}/category-description/{categoryName}")
    public ResponseEntity<Void> deleteCategoryDescription(@PathVariable Long sectionId,
                                                          @PathVariable String categoryName) {
        assessmentService.deleteCategoryDescription(sectionId, categoryName);
        return ResponseEntity.ok().build();
    }
}
