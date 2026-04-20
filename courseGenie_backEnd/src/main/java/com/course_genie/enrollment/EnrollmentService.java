package com.course_genie.enrollment;

import com.course_genie.student.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentDTOMapper enrollmentDTOMapper;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             EnrollmentDTOMapper enrollmentDTOMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentDTOMapper = enrollmentDTOMapper;
    }

    public List<EnrollmentDTO> getEnrollmentsBySection(Long sectionId) {
        return enrollmentRepository
                .findEnrollmentBySectionSectionId(sectionId)
                .stream()
                .map(enrollmentDTOMapper)
                .toList();
    }


}
