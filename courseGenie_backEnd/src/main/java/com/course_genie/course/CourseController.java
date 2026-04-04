package com.course_genie.course;

import com.course_genie.section.CreateSectionRequest;
import com.course_genie.section.UpdateSectionRequest;
import com.course_genie.section.SectionDTO;
import com.course_genie.assessment.AssessmentDTO;
import com.course_genie.course.CourseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{courseCode}/sections")
    public ResponseEntity<SectionDTO> createSection(
            @PathVariable String courseCode,
            @RequestBody CreateSectionRequest request) {
        return ResponseEntity.ok(courseService.createSection(courseCode, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{courseCode}/sections/{sectionId}")
    public ResponseEntity<SectionDTO> updateSection(
            @PathVariable String courseCode,
            @PathVariable Long sectionId,
            @RequestBody UpdateSectionRequest request) {
        return ResponseEntity.ok(courseService.updateSection(courseCode, sectionId, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{courseCode}/sections/{sectionId}")
    public ResponseEntity<Void> deleteSection(
            @PathVariable String courseCode,
            @PathVariable Long sectionId) {
        courseService.deleteSection(courseCode, sectionId);
        return ResponseEntity.noContent().build();
    }
}
