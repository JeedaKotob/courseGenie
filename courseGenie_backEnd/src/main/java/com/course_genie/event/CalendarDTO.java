package com.course_genie.event;
import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record CalendarDTO (
        String title,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        String room,
        String type
) {}
