package com.course_genie.semester;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long semesterId;

    private String semesterName;

    private LocalDate syllabusDueDate;
}
