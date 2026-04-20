package com.course_genie.examSchedule;

import java.time.LocalDate;
import java.util.List;

public record ExamScheduleSaveRequest(
        LocalDate examDate,
        List<ExamScheduleAssignmentRequest> assignments
) {
}
