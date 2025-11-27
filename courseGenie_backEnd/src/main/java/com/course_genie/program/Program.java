package com.course_genie.program;
import com.course_genie.department.Department;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programId;

    private String programName;

    @Column(length=2000)
    private String description;

    @ManyToOne
    @JoinColumn(name="department_id", nullable=false)
    private Department department;
}
