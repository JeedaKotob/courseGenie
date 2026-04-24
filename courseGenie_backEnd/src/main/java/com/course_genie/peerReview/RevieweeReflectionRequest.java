package com.course_genie.peerReview;

public record RevieweeReflectionRequest(
        Long peerReviewId,
        Long revieweeId,
        String actionPlan
) {
}
