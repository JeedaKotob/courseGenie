package com.course_genie.grade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    // Create
    @PostMapping
    public ResponseEntity<Boolean> createGrade(@RequestBody List<GradeDTO> gradesDTO) {
        return ResponseEntity.ok(gradeService.createGrade(gradesDTO));
    }

    // Read
    @GetMapping
    public ResponseEntity<List<GradeDTO>> getAllGrades() {
        return ResponseEntity.ok(gradeService.getAllGrades());
    }

    @GetMapping("/{gradeId}")
    public ResponseEntity<GradeDTO> getGradeById(@PathVariable long gradeId) {
        return ResponseEntity.ok(gradeService.getGradeById(gradeId));
    }

    // Update
    @PutMapping
    public ResponseEntity<GradeDTO> updateGrade(@RequestBody GradeDTO gradeDetails) {
        return ResponseEntity.ok(gradeService.updateGrade(gradeDetails));
    }

    // Delete
    @DeleteMapping("/{gradeId}")
    public ResponseEntity<Boolean> deleteGrade(@PathVariable long gradeId) {
        gradeService.deleteGrade(gradeId);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
