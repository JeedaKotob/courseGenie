package com.course_genie.plo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface PLORepository extends JpaRepository<PLO, Long> {
    @Query("""
            select distinct p
            from PLO p
            left join fetch p.clos
            where p.program.department.departmentId = :departmentId
            """)
    List<PLO> findByDepartmentIdWithClos(@Param("departmentId") Long departmentId);

    @Query("""
            select distinct p
            from PLO p
            left join fetch p.clos c
            where c.course.courseId = :courseId
            """)
    List<PLO> findMappedToCourseClos(@Param("courseId") Long courseId);

    @Query("""
            select distinct p
            from PLO p
            left join fetch p.clos
            where p.program.programId in :programIds
            """)
    List<PLO> findByProgramIdsWithClos(@Param("programIds") Collection<Long> programIds);

    @Query("""
            select distinct p
            from PLO p
            left join fetch p.clos
            """)
    List<PLO> findAllWithClos();
}
