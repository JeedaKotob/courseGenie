package com.course_genie.assessment;

import com.course_genie.section.Section;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category_description", uniqueConstraints = @UniqueConstraint(columnNames = {"section_id", "category_name"}))

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    private String description;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;
}