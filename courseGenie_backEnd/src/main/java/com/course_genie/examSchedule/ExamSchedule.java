package com.course_genie.examSchedule;

import com.course_genie.course.Course;
import com.course_genie.examRoom.ExamRoom;
import com.course_genie.semester.Semester;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"examDate", "startTime", "endTime", "course_id", "semester_id"})
        }
)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examScheduleId;

    private LocalDate examDate;

    private LocalTime startTime;

    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToMany
    @JoinTable(
            name = "exam_schedule_rooms",
            joinColumns = @JoinColumn(name = "exam_schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    @Builder.Default
    private Set<ExamRoom> availableRooms = new HashSet<>();
}
