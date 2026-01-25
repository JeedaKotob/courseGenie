package com.course_genie.enrollment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getEnrollmentsForSection(Long sectionId) {
        return enrollmentRepository.findEnrollmentBySectionSectionIdAndStatus(
                sectionId,
                Enrollment.EnrollmentStatus.ENROLLED
        );
    }
}
