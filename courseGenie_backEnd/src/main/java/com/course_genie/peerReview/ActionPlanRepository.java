package com.course_genie.peerReview;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActionPlanRepository extends JpaRepository<ActionPlan, Long> {
    Optional<ActionPlan> findByPeerReviewPeerReviewId(Long peerReviewId);
}
