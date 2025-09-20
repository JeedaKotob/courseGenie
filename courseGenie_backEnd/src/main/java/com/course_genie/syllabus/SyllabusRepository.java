package com.course_genie.syllabus;

import com.course_genie.section.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SyllabusRepository extends JpaRepository<Syllabus, Long> {
    Optional<Syllabus> findSyllabusBySection(Section section);
}
