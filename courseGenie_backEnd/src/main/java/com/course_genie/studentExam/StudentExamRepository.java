package com.course_genie.studentExam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentExamRepository extends JpaRepository<StudentExam, Long> {
    void deleteByEnrollmentSectionSectionId(Long sectionId);
}
