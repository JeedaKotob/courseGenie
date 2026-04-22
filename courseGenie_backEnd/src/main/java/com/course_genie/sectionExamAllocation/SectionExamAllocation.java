package com.course_genie.sectionExamAllocation;

import com.course_genie.enrollment.Enrollment;
import com.course_genie.examRoom.ExamRoom;
import com.course_genie.examSchedule.ExamSchedule;
import com.course_genie.section.Section;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"exam_schedule_id", "section_id", "enrollment_id"})
        }
)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionExamAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionExamAllocationId;

    @ManyToOne
    @JoinColumn(name = "exam_schedule_id", nullable = false)
    private ExamSchedule examSchedule;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private ExamRoom examRoom;
}
