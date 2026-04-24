package com.course_genie.peerReview;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeerReviewAssignmentRepository extends JpaRepository<PeerReviewAssignment, Long> {
    List<PeerReviewAssignment> findByDepartmentDepartmentNameIgnoreCase(String departmentName);
    void deleteByDepartmentDepartmentNameIgnoreCase(String departmentName);
}
