package com.course_genie.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByUserUserIdAndEventDateBetween(
            Long userId,
            LocalDate startDate,
            LocalDate endDate
    );

}