package com.course_genie.peerReview;

public record PeerReviewPairRequest(
        Long reviewerId,
        Long revieweeId
) {
}
