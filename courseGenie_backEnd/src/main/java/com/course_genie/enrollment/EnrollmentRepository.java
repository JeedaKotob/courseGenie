package com.course_genie.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findEnrollmentBySectionSectionIdAndStatus(
            Long sectionId,
            Enrollment.EnrollmentStatus status
    );
    List<Enrollment> findEnrollmentBySectionSectionId(long sectionId);
}
