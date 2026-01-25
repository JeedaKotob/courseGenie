package com.course_genie.course;


import com.course_genie.assessment.AssessmentDTO;
import java.util.List;
import com.course_genie.course.CourseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO createCourse = courseService.createCourse(courseDTO);
        return ResponseEntity.ok(createCourse);
    }

    @GetMapping("/code/{courseCode}")
    public ResponseEntity<CourseDTO> getCourseByCourseCode(@PathVariable String courseCode) {
        return ResponseEntity.ok(courseService.getCourseByCode(courseCode));
    }


    @GetMapping
    public ResponseEntity<Map<String, Set<CourseDTO>>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping(path = "/{professorId}")
    public ResponseEntity<Map<String, Set<CourseDTO>>> getCoursesByProfessorId(@PathVariable Long professorId) {
        return ResponseEntity.ok(courseService.getCoursesByProfessorId(professorId));
    }

    @GetMapping(path = "/{courseCode}/section/{sectionCode}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable String courseCode, @PathVariable String sectionCode) {
        return ResponseEntity.ok(courseService.getCourseByCodeAndSectionCode(courseCode, sectionCode));
    }

    /**
     * GET /api/courses/dtos
     * Returns a flat list of all courses as CourseDTOs.
     */
    @GetMapping(path = "/course")
    public ResponseEntity<List<CourseDTO>> getAllCourseDTOs() {
        List<CourseDTO> dtos = courseService.getAllCourse();
        System.out.println(courseService.getAllCourse());
        return ResponseEntity.ok(dtos);
    }
}
