package com.course_genie.examSchedule;

import lombok.Builder;

@Builder
public record RoomSeatAvailabilityDTO(
        long roomId,
        int remainingSeats
) {
}
