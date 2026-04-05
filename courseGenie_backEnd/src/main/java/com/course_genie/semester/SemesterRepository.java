package com.course_genie.semester;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    Optional<Semester> findBySemesterNameIgnoreCase(String semesterName);

    List<Semester> findAllByOrderBySemesterNameDesc();
}
