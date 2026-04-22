package com.course_genie.examSchedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExamScheduleRepository extends JpaRepository<ExamSchedule, Long> {
    List<ExamSchedule> findByExamDate(LocalDate examDate);

    List<ExamSchedule> findByCourseCourseIdAndSemesterSemesterIdOrderByExamDateDesc(Long courseId, Long semesterId);

    void deleteByExamDate(LocalDate examDate);
}
