package com.course_genie.car;
import com.course_genie.section.Section;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @OneToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @Column(columnDefinition="TEXT")
    private String studentFeedbackSynopsis;

    @Column(columnDefinition="TEXT")
    private String impedimentsAnalysis;

    @Column(columnDefinition="TEXT")
    private String suggestedModifications;

    @Column(columnDefinition="TEXT")
    private String aiReflection;

    @Column(columnDefinition="TEXT")
    private String cloBenchmarkReflection;

    private boolean submitted;
    private LocalDate submissionDate;
}