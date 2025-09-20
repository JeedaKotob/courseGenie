package com.course_genie.professor;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProfessorDTOMapper implements Function<Professor, ProfessorDTO> {
    @Override
    public ProfessorDTO apply(Professor professor) {
        return ProfessorDTO.builder()
                .professorId(professor.getProfessorId())
                .firstName(professor.getFirstName())
                .lastName(professor.getLastName())
                .userName(professor.getUserName())
                .email(professor.getEmail())
                .phone(professor.getPhone())
                .officeHours(professor.getOfficeHours())
                .office(professor.getOffice())
                .roles(professor.getRoles())
                .build();
    }
}
