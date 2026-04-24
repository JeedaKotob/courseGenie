package com.course_genie.peerReview;

public record ReviewerSubmitPeerReviewRequest(
        Long assignmentId,
        Long reviewerId,
        Integer alignmentScore,
        String alignmentComment,
        Integer assessmentDesignScore,
        String assessmentDesignComment,
        Integer gradingClarityScore,
        String gradingClarityComment,
        Integer feedbackEfficiencyScore,
        String feedbackEfficiencyComment,
        String courseGradeDistributionNote,
        String courseReflectionNote,
        String innovationJourneyNote,
        String otherNote,
        String summary
) {
}
