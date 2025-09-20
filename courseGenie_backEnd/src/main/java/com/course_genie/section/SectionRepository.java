package com.course_genie.section;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Optional<List<Section>> findByCourseCourseId(Long courseCourseId);

    Optional<List<Section>> findByCourseCourseIdAndProfessorProfessorId(Long courseCourseId, Long professorId);

    Optional<Section> findSectionByCode(String code);

    Optional<Section> findSectionByCodeAndCourseCode(String sectionCode, String courseCode);

    Optional<List<Section>> findSectionByCourseCourseIdAndProfessorProfessorIdAndConfigured(long courseId, long professorId, boolean isConfigure);
}
