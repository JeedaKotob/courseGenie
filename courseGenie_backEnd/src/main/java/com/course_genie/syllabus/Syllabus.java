package com.course_genie.syllabus;

import com.course_genie.course.Course;
import com.course_genie.section.Section;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Syllabus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long syllabusId;
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String content;
    @OneToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    private boolean submitted;

    public Syllabus(long syllabusId) {
        this.syllabusId = syllabusId;
    }
}
