package com.course_genie.booking;

import com.course_genie.assessment.Assessment;
import com.course_genie.examRoom.ExamRoom;
import com.course_genie.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    private int nbrOfStudents;

    private Date date;

    private LocalTime startTime;

    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name="room_id",nullable=false)
    private ExamRoom examRoom;

    @ManyToOne
    @JoinColumn(name="proctor_id",nullable=false)
    private User professor;

    @OneToOne
    @JoinColumn(name="assessment_id",nullable=false)
    private Assessment assessment;



}
