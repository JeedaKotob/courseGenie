package com.course_genie.grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    @Query("SELECT g FROM Grade g WHERE g.student.studentId = :studentId AND g.assessment.section.sectionId = :sectionId")
    Optional<List<Grade>> findGradeByStudentIdAndSectionId(@Param("studentId") String studentId, @Param("sectionId") Long sectionId);

    boolean existsByAssessmentAssessmentIdAndStudentStudentId(long assessmentId, String studentId);

    @Query(value = "SELECT COUNT(grade_id) from grade where assessment_id = :assessmentId and student_id = :studentId", nativeQuery = true)
    int countGradesByAssessmentAssessmentIdAndStudentStudentId(@Param("assessmentId") long assessmentId, @Param("studentId") String studentId);

    Optional<Grade> findGradeByAssessmentAssessmentIdAndStudentStudentId(long assessmentId, String studentId);

    Optional<List<Grade>> findGradeByAssessmentAssessmentId(long assessmentId);
}
