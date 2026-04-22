package com.course_genie.sectionExamAllocation;

import java.util.List;

public record SaveProfessorExamAllocationRequest(
        Long examScheduleId,
        List<StudentRoomAssignmentRequest> assignments
) {
}
