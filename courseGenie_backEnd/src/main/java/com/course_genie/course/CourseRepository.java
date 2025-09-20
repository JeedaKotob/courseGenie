package com.course_genie.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findCourseByCode(String sectionCode);

    @Query(value = "SELECT DISTINCT c.* FROM course c " +
            "INNER JOIN section s on s.course_id = c.course_id " +
            "WHERE s.professor_id = :professorId", nativeQuery = true)
    List<Course> findCourseByProfessorId(@Param("professorId") Long professorId);
}
