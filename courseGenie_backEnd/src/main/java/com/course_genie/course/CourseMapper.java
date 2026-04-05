package com.course_genie.course;

import com.course_genie.department.Department;
import com.course_genie.department.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CourseMapper implements Function<CourseDTO, Course> {
    private final DepartmentRepository departmentRepository;

    public CourseMapper(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Course apply(CourseDTO courseDTO) {
        Department department = null;

        if (courseDTO.departmentId() != null) {
            department = departmentRepository.findById(courseDTO.departmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        } else if (courseDTO.departmentName() != null && !courseDTO.departmentName().isBlank()) {
            department = departmentRepository.findByDepartmentNameIgnoreCase(courseDTO.departmentName().trim())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        }

        return Course.builder()
                .courseId(courseDTO.courseId())
                .code(courseDTO.code())
                .name(courseDTO.name())
                .description(courseDTO.description())
                .credits(courseDTO.credits())
                .department(department)
                .discipline(courseDTO.discipline())

                .build();
    }
}
