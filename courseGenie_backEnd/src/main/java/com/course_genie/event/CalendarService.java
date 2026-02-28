package com.course_genie.event;

import com.course_genie.section.Section;
import com.course_genie.section.SectionRepository;
import com.course_genie.semester.Semester;
import com.course_genie.user.User;
import com.course_genie.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final EventRepository eventRepository;
    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    public List<CalendarDTO> getCalendarForRange(
            Long professorId,
            LocalDate startDate,
            LocalDate endDate
    ) {

        List<CalendarDTO> results = new ArrayList<>();

        List<Event> events =
                eventRepository.findByUserUserIdAndEventDateBetween(
                        professorId,
                        startDate,
                        endDate
                );

        for (Event event : events) {
            results.add(
                    CalendarDTO.builder()
                            .title(event.getEventName())
                            .date(event.getEventDate())
                            .startTime(event.getStartTime())
                            .endTime(event.getEndTime())
                            .room(event.getRoom())
                            .type("EVENT")
                            .build()
            );
        }

        List<Section> sections =
                sectionRepository.findByProfessorUserId(professorId);

        for (Section section : sections) {

            Semester semester = section.getSemester();
            if (semester == null) continue;

            LocalDate semesterStart = semester.getStartDate();
            LocalDate semesterEnd = semester.getEndDate();

            if (semesterStart == null || semesterEnd == null) {
                continue;
            }

            for (LocalDate date = startDate;
                 !date.isAfter(endDate);
                 date = date.plusDays(1)) {

                if (date.isBefore(semesterStart) ||
                        date.isAfter(semesterEnd)) {
                    continue;
                }

                if (section.getMeetingDays() != null &&
                        section.getMeetingDays().contains(date.getDayOfWeek())) {

                    results.add(
                            CalendarDTO.builder()
                                    .title(section.getCourse().getName())
                                    .date(date)
                                    .startTime(section.getMeetingStartTime())
                                    .endTime(section.getMeetingEndTime())
                                    .room(section.getRoom())
                                    .type("LECTURE")
                                    .build()
                    );
                }
            }
        }

        return results.stream()
                .sorted(Comparator
                        .comparing(CalendarDTO::date)
                        .thenComparing(CalendarDTO::startTime))
                .toList();
    }

    public void createEvent(EventDTO eventDTO) {
        User user = userRepository.findById(eventDTO.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = Event.builder()
                .eventName(eventDTO.eventName())
                .eventDate(eventDTO.eventDate())
                .startTime(eventDTO.startTime())
                .endTime(eventDTO.endTime())
                .room(eventDTO.room())
                .user(user)
                .build();

        eventRepository.save(event);
    }
}