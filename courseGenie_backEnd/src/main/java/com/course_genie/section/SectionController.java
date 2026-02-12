package com.course_genie.section;

import com.course_genie.student.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PutMapping("/{sectionId}")
    public ResponseEntity<Boolean> saveConfiguration(@PathVariable Long sectionId) {
        return ResponseEntity.ok(sectionService.saveConfiguration(sectionId));
    }

    @GetMapping("/{sectionId}/teaching-methodology")
    public ResponseEntity<String> getTeachingMethodology(@PathVariable Long sectionId) {
        return ResponseEntity.ok(sectionService.getTeachingMethodology(sectionId));
    }

    @PostMapping("/{sectionId}/teaching-methodology")
    public ResponseEntity<Void> setTeachingMethodology(
            @PathVariable Long sectionId,
            @RequestBody String methodologyText) {
        sectionService.setTeachingMethodology(sectionId, methodologyText);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{sectionId}/students")
    public ResponseEntity<List<Student>> getStudentsBySection(@PathVariable Long sectionId) {
        return ResponseEntity.ok(sectionService.getStudentsBySection(sectionId));
    }

    @GetMapping("/{sectionId}")
    public SectionDTO getSectionById(@PathVariable Long sectionId) {
        return sectionService.getSectionById(sectionId);
    }

}
