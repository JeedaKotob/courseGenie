package com.course_genie.professor;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.function.Function;

@Service
public class ProfessorMapper implements Function<ProfessorDTO, Professor> {
    @Override
    public Professor apply(ProfessorDTO professorDTO) {
        return Professor.builder()
                .professorId(professorDTO.getProfessorId())
                .firstName(professorDTO.getFirstName())
                .lastName(professorDTO.getLastName())
                .userName(professorDTO.getUserName())
                .email(professorDTO.getEmail())
                .phone(professorDTO.getPhone())
                .officeHours(professorDTO.getOfficeHours())
                .office(professorDTO.getOffice())
                .roles((professorDTO.getRoles()))
                .build();
    }
}
