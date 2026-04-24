package com.course_genie.peerReview;

public record PeerReviewAssignmentDTO(
        Long assignmentId,
        Long reviewerId,
        String reviewerName,
        Long revieweeId,
        String revieweeName,
        Long revieweeSectionId,
        String courseCode,
        String courseName,
        String sectionCode,
        String departmentName,
        String pairingSource,
        String progressStatus
) {
}
