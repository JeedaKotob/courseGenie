package com.course_genie.enrollment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/{sectionId}/enrollments")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsBySection(
            @PathVariable long sectionId) {

        return ResponseEntity.ok(
                enrollmentService.getEnrollmentsBySection(sectionId)
        );
    }

}
