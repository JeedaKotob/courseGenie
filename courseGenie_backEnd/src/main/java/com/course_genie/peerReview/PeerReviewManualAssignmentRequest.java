package com.course_genie.peerReview;

import java.util.List;

public record PeerReviewManualAssignmentRequest(
        String departmentName,
        List<PeerReviewPairRequest> assignments
) {
}
