package com.course_genie.professor;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long professorId;

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String office;
    private String phone;
    private String officeHours;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "professor_roles", joinColumns = @JoinColumn(name = "professor_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();
}