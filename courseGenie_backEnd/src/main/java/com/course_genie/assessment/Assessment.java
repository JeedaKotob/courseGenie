package com.course_genie.assessment;

import com.course_genie.clo.CLO;
import com.course_genie.section.Section;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name= "assessment")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long assessmentId;
    private String name;
    private String categoryName;
    private String shortName;
    private int week;
    private int maxPoints;
    private int topic;


    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;
    @ManyToMany
    @JoinTable(
            name = "assessment_clo", // Name of the join table
            joinColumns = @JoinColumn(name = "assessment_id"), // Foreign key for Assessment
            inverseJoinColumns = @JoinColumn(name = "clo_id") // Foreign key for CLO
    )
    private List<CLO> clos;

    public Assessment(long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Assessment(String name, String category, String shortName, int maxPoints, Section section,int week, List<CLO> clos) {
        this.name = name;
        this.categoryName = category;
        this.shortName = shortName;
        this.maxPoints = maxPoints;
        this.section = section;
        this.week = week;

        this.clos = clos;
    }
}
