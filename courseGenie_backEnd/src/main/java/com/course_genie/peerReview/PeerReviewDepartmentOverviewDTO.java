package com.course_genie.peerReview;

import java.util.List;

public record PeerReviewDepartmentOverviewDTO(
        String departmentName,
        List<ProfessorOptionDTO> professors,
        List<RevieweeSectionOptionDTO> revieweeSections,
        int assignmentCount
) {
}
