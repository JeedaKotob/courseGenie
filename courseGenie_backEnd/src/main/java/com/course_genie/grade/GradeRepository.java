package com.course_genie.grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    @Query("SELECT g FROM Grade g WHERE g.enrollment.enrollmentId = :enrollmentId")
    Optional<List<Grade>> findGradeByEnrollmentEnrollmentId(@Param("enrollmentId") long enrollmentId);

    boolean existsByAssessmentAssessmentIdAndEnrollmentEnrollmentId(long assessmentId, long enrollmentId);

    @Query(value = "SELECT COUNT(grade_id) from grade where assessment_id = :assessmentId and enrollment_id = :enrollmentId", nativeQuery = true)
    int countGradesByAssessmentAssessmentIdAndEnrollmentEnrollmentId(@Param("assessmentId") long assessmentId, @Param("enrollmentId") long enrollmentId);

    Optional<Grade> findGradeByAssessmentAssessmentIdAndEnrollmentEnrollmentId(long assessmentId, long enrollmentId);

    Optional<List<Grade>> findGradeByAssessmentAssessmentId(long assessmentId);

    Optional<List<Grade>> findGradeByEnrollmentSectionSectionId(long sectionId);


}
