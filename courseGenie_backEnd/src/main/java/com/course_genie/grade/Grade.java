package com.course_genie.grade;

import com.course_genie.assessment.Assessment;
import com.course_genie.student.Student;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gradeId;
    private double score;
    @ManyToOne
    @JoinColumn(name = "assessment_id", nullable = false)
    private Assessment assessment;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public Grade(long gradeId) {
        this.gradeId = gradeId;
    }
}
