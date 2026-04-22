package com.course_genie.sectionExamAllocation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/professor-exam-allocations")
public class ProfessorExamAllocationController {
    private final ProfessorExamAllocationService professorExamAllocationService;

    public ProfessorExamAllocationController(ProfessorExamAllocationService professorExamAllocationService) {
        this.professorExamAllocationService = professorExamAllocationService;
    }

    @GetMapping("/{sectionId}")
    public ResponseEntity<ProfessorExamAllocationDTO> getSectionAllocation(
            @PathVariable Long sectionId,
            @RequestParam Long professorId
    ) {
        return ResponseEntity.ok(professorExamAllocationService.getProfessorAllocationView(sectionId, professorId));
    }

    @PutMapping("/{sectionId}")
    public ResponseEntity<ProfessorExamAllocationDTO> saveSectionAllocation(
            @PathVariable Long sectionId,
            @RequestParam Long professorId,
            @RequestBody SaveProfessorExamAllocationRequest request
    ) {
        return ResponseEntity.ok(professorExamAllocationService.saveProfessorAllocations(sectionId, professorId, request));
    }
}
