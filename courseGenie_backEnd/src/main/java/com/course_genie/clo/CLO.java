package com.course_genie.clo;

import com.course_genie.assessment.Assessment;
import com.course_genie.course.Course;
import com.course_genie.section.Section;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CLO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cloId;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public CLO(long cloId) {
        this.cloId = cloId;
    }
}
