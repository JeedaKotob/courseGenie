package com.course_genie.enrollment;

import com.course_genie.section.Section;
import com.course_genie.student.Student;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long enrollmentId;

    @ManyToOne
    @JoinColumn(name="student_id",nullable=false)
    private Student student;

    @ManyToOne
    @JoinColumn(name="section_id",nullable=false)
    private Section section;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    public enum EnrollmentStatus {
        ENROLLED,
        WITHDRAWN
        // anythong more ??
    }

    public Enrollment(long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }
}
