package com.course_genie.student;

import com.course_genie.grade.GradeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/section/{sectionId}/grades")
    public ResponseEntity<Map<StudentDTO, List<GradeDTO>>> getStudentsWithGrades(
            @PathVariable Long sectionId) {

        return ResponseEntity.ok(
                studentService.getAllStudentsWithGradesBySection(sectionId)
        );
    }
}