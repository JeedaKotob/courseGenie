package com.course_genie.peerReview;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PeerReviewRepository extends JpaRepository<PeerReview, Long> {
    Optional<PeerReview> findByAssignmentId(Long assignmentId);
    List<PeerReview> findByReviewerUserId(Long reviewerId);
}
