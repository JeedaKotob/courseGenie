package com.course_genie.professor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    long professorId;
    String firstName;
    String lastName;
    String userName;
    String email;
    String jwtToken;
    String phone;
    String officeHours;
    String office;
    Set<String> roles;

}
