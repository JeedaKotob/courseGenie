package com.course_genie.syllabus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/syllabuses")
public class SyllabusController {
    private final SyllabusService syllabusService;

    public SyllabusController(SyllabusService syllabusService) {
        this.syllabusService = syllabusService;
    }

    // Create
    @PostMapping
    public ResponseEntity<SyllabusDTO> createSyllabus(@RequestBody SyllabusDTO syllabusDTO) {
        return ResponseEntity.ok(syllabusService.createSyllabus(syllabusDTO));
    }

    // Read
    @GetMapping
    public ResponseEntity<List<SyllabusDTO>> getAllSyllabuses() {
        return ResponseEntity.ok(syllabusService.getAllSyllabuses());
    }

    @GetMapping("/{syllabusId}")
    public ResponseEntity<SyllabusDTO> getSyllabusById(@PathVariable long syllabusId) {
        return ResponseEntity.ok(syllabusService.getSyllabusById(syllabusId));
    }

    // Update
    @PutMapping
    public ResponseEntity<SyllabusDTO> updateSyllabus(@RequestBody SyllabusDTO syllabusDetails) {
        return ResponseEntity.ok(syllabusService.updateSyllabus(syllabusDetails));
    }

    // Delete
    @DeleteMapping("/{syllabusId}")
    public ResponseEntity<Boolean> deleteSyllabus(@PathVariable long syllabusId) {
        syllabusService.deleteSyllabus(syllabusId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    // Generate Syllabus Automatically
    @PostMapping("/generate/{sectionId}")
    public ResponseEntity<SyllabusDTO> generateSyllabus(@PathVariable long sectionId) {
        return ResponseEntity.ok(syllabusService.generateSyllabus(sectionId));
    }

    @PostMapping("/submit/{syllabusId}")
    public ResponseEntity<Void> submitSyllabus(@PathVariable long syllabusId) {
        syllabusService.submitSyllabus(syllabusId);
        return ResponseEntity.ok().build();
    }

}
