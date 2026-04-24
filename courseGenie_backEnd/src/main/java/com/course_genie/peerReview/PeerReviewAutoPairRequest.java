package com.course_genie.peerReview;

public record PeerReviewAutoPairRequest(
        String departmentName,
        Integer reviewsPerProfessor
) {
}
