package com.course_genie.sectionExamAllocation;

public record StudentRoomAssignmentRequest(
        Long enrollmentId,
        Long roomId
) {
}
