package com.course_genie.course;

import com.course_genie.assessment.Assessment;
import com.course_genie.professor.Professor;
import com.course_genie.syllabus.Syllabus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long courseId;
    private String code;
    private String name;
    @Column(length = 2000)
    private String description;
    private String credits;

    private String discipline;
    private String courseApprovalDate; // Could also be a Date if desired
    private String lastRevisionDate;   // Could also be a Date if desired


    private boolean undergraduate;
    private boolean newCourse;
    private boolean courseDeletion; // Use a boolean if it only indicates a flag
    private boolean graduate;
    private String prerequisites;
    private String corequisites;
    private Boolean designatedInnovationJourneyCourse;
                                                                        

    public Course(long courseId) {
        this.courseId = courseId;
    }
}
