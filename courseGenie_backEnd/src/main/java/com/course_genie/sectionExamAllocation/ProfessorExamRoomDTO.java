package com.course_genie.sectionExamAllocation;

public record ProfessorExamRoomDTO(
        long roomId,
        String roomNumber,
        String roomType,
        int capacity,
        long assignedCountTotal,
        long assignedCountInSection
) {
}
