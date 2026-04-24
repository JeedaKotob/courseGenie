package com.course_genie.peerReview;

import java.time.LocalDateTime;

public record RevieweeReceivedReviewDTO(
        Long peerReviewId,
        Long revieweeId,
        String reviewerName,
        String courseCode,
        String courseName,
        String sectionCode,
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
        String summary,
        LocalDateTime submittedAt,
        String actionPlan,
        boolean reflectionSubmitted,
        LocalDateTime reflectionSubmittedAt
) {
}
