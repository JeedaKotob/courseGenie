package com.course_genie.examSchedule;

import java.util.List;

public record ExamScheduleAssignmentRequest(
        long examScheduleId,
        List<Long> roomIds
) {
}
