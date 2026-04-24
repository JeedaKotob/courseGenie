package com.course_genie.peerReview;

public record PeerReviewAssignmentDTO(
        Long assignmentId,
        Long reviewerId,
        String reviewerName,
        Long revieweeId,
        String revieweeName,
        String departmentName,
        String pairingSource
) {
}
