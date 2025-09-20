package com.course_genie.clo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CLORepository extends JpaRepository<CLO, Long> {
    Optional<List<CLO>> findCLOByCourseCourseId(long courseId);

    @Query(value = "SELECT DISTINCT c.* FROM clo c " +
            "INNER JOIN assessment_clo ac on ac.clo_id = c.clo_id " +
            "INNER JOIN assessment a on a.assessment_id = ac.assessment_id " +
            "WHERE a.section_id = :sectionId", nativeQuery = true)
    Optional<ArrayList<CLO>> findCLOBySectionId(@Param("sectionId") Long sectionId);
}
