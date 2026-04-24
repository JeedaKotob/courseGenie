package com.course_genie.peerReview;

import java.util.List;

public record PeerReviewPublishResponseDTO(
        boolean globallyVisible,
        List<String> unassignedDepartments
) {
}
