package com.course_genie.peerReview;

public record PeerReviewProfessorVisibilityDTO(
        boolean visible,
        boolean departmentAssigned,
        String warning
) {
}
