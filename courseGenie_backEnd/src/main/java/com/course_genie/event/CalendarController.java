package com.course_genie.event;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping
    public List<CalendarDTO> getCalendar(
            @RequestParam Long userId,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end
    ) {
        return calendarService.getCalendarForRange(userId, start, end);
    }
}