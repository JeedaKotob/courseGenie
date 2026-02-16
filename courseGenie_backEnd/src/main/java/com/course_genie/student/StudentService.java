package com.course_genie.student;

import com.course_genie.grade.GradeDTO;
import com.course_genie.grade.GradeDTOMapper;
import com.course_genie.grade.GradeRepository;
import org.springframework.stereotype.Service;
import com.course_genie.enrollment.Enrollment;
import com.course_genie.enrollment.EnrollmentRepository;
import com.course_genie.grade.Grade;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service
public class StudentService {

    private final EnrollmentRepository enrollmentRepository;
    private final GradeRepository gradeRepository;
    private final StudentDTOMapper studentDTOMapper;
    private final GradeDTOMapper gradeDTOMapper;

    public StudentService(EnrollmentRepository enrollmentRepository,
                          GradeRepository gradeRepository,
                          StudentDTOMapper studentDTOMapper,
                          GradeDTOMapper gradeDTOMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.gradeRepository = gradeRepository;
        this.studentDTOMapper = studentDTOMapper;
        this.gradeDTOMapper = gradeDTOMapper;
    }

    public Map<StudentDTO, List<GradeDTO>> getAllStudentsWithGradesBySection(Long sectionId) {
        Map<StudentDTO, List<GradeDTO>> result = new HashMap<>();

        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentBySectionSectionId(sectionId);

        for (Enrollment enrollment : enrollments) {
            StudentDTO studentDTO = studentDTOMapper.apply(enrollment.getStudent());

            // 3. Find Grades using the ENROLLMENT ID (not student ID)
            List<Grade> grades = gradeRepository.findGradeByEnrollmentEnrollmentId(enrollment.getEnrollmentId()).orElse(List.of());

            List<GradeDTO> gradeDTOs = grades.stream()
                    .map(gradeDTOMapper)
                    .collect(Collectors.toList());

            result.put(studentDTO, gradeDTOs);
        }

        return result;
    }
}