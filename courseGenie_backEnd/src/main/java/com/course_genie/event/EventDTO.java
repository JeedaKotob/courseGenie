package com.course_genie.event;
import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record EventDTO (
        Long userId,
        String eventName,
        LocalDate eventDate,
        LocalTime startTime,
        LocalTime endTime,
        String room
) {}
