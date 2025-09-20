package com.course_genie.clo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clos")
public class CLOController {

    private final CLOService cloService;

    public CLOController(CLOService cloService) {
        this.cloService = cloService;
    }

    // Create
    @PostMapping
    public ResponseEntity<CLODTO> createClo(@RequestBody CLODTO cloDTO) {
        return ResponseEntity.ok(cloService.createClo(cloDTO));
    }

    // Read
    @GetMapping
    public ResponseEntity<List<CLODTO>> getAllClos() {
        return ResponseEntity.ok(cloService.getAllClos());
    }

    @GetMapping("/{cloId}")
    public ResponseEntity<CLODTO> getCloById(@PathVariable long cloId) {
        return ResponseEntity.ok(cloService.getCloById(cloId));
    }

    // Update
    @PutMapping
    public ResponseEntity<CLODTO> updateClo(@RequestBody CLODTO cloDetails) {
        return ResponseEntity.ok(cloService.updateClo(cloDetails));

    }

    // Delete
    @DeleteMapping("/{cloId}")
    public ResponseEntity<Boolean> deleteClo(@PathVariable long cloId) {
        cloService.deleteClo(cloId);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
