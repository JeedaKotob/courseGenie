package com.course_genie.benchmark;
import com.course_genie.program.Program;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Benchmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long benchmarkId;
    private String benchmarkType;
    private String description;
    private int threshold;
    private Integer percentage;

//    @ManyToOne
//    @JoinColumn(name="program_id",nullable=false)
//    private Program program;

}
