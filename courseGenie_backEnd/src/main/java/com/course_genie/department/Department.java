package com.course_genie.department;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long departmentId;

    private String departmentName; // do we stick to this namin istead of name and will i have to change all entities?

    @Column(length=2000)
    private String description;

    //ADD HEAD_ID (prof?)
}
