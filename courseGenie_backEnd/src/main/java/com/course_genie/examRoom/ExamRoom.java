package com.course_genie.examRoom;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private int capacity;

    private String roomNumber;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    public enum RoomType {
        CLASSROOM,
        LAB
    }

}
