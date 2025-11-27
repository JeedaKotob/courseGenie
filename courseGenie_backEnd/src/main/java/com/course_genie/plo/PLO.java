package com.course_genie.plo;
import com.course_genie.clo.CLO;
import com.course_genie.program.Program;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PLO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ploId;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name="program_id",nullable=false)
    private Program program;

    @ManyToMany
    @JoinTable(
            name="plo_clo",
            joinColumns=@JoinColumn(name="plo_id"),
            inverseJoinColumns=@JoinColumn(name="clo_id")
    )
    private Set<CLO> clos;
}
