package com.course_genie.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r=:role")
    List<User> findByRoles(@Param("role") String role);

    @Query("""
        SELECT DISTINCT u
        FROM User u
        JOIN u.roles r
        WHERE r = 'ROLE_PROFESSOR'
          AND LOWER(TRIM(u.department.departmentName)) = LOWER(TRIM(:departmentName))
    """)
    List<User> findProfessorsByDepartmentName(@Param("departmentName") String departmentName);
}
