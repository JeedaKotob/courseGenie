package com.course_genie.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r=:role")
    List<User> findByRoles(@Param("role") String role);

    @Query("SELECT DISTINCT s.professor FROM Syllabus sy JOIN sy.section s WHERE sy.submitted=false")
    List<User> findProfessorsWithUnsubmittedSyllabi();

}
