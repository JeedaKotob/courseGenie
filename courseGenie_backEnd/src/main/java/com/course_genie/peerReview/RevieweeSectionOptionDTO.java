package com.course_genie.peerReview;

public record RevieweeSectionOptionDTO(
        Long sectionId,
        String courseCode,
        String courseName,
        String sectionCode,
        Long revieweeId,
        String revieweeName
) {
}
