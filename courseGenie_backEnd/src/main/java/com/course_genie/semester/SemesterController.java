package com.course_genie.semester;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/semesters")
public class SemesterController {
    private final SemesterService semesterService;

    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllSemesters() {
        return ResponseEntity.ok(semesterService.getAllSemesterNames());
    }

    @GetMapping("/current")
    public ResponseEntity<String> getCurrentSemesterName() {
        return ResponseEntity.ok(semesterService.getCurrentSemesterOrThrow().getSemesterName());
    }
}
