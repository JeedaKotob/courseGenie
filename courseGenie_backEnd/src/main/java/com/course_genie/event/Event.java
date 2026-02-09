package com.course_genie.event;


import com.course_genie.user.User;

import java.sql.Date;
import java.sql.Time;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventId;

    private String eventName;
    private Date eventDate;
    private Time startTime;
    private Time endTime;
    private String room; // make a room entity?

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // user cuz both admin and profs will have calendars

}
