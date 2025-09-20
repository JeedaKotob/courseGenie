package com.course_genie.section;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sections")
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PutMapping(path = "/{sectionId}")
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
}
