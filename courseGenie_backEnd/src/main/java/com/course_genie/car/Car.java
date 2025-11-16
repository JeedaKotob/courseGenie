package com.course_genie.car;
import com.course_genie.section.Section;
import lombok.*;
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

    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String content;

    @OneToOne
    @JoinColumn(name="section_id",nullable=false)
    private Section section;

    private Date dueDate;

    private Date submissionDate;

    private boolean submitted;

}
