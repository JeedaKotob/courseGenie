package com.course_genie.peerReview;

public record PeerReviewProgressSummaryDTO(
        int totalAssignments,
        int notStarted,
        int reviewerFinished,
        int done,
        double completionPercentage
) {
}
