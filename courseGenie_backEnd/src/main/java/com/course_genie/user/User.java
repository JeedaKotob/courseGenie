package com.course_genie.user;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long userId;

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String office;
    private String phone;
    private String officeHours;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    public String getFullName(){
        String first = this.firstName;
        String last = this.lastName;
        String full = ((first == null ? "" : first) + " " + (last == null ? "" : last)).trim();
        return full;
    }
}