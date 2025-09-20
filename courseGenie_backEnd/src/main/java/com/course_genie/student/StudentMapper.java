package com.course_genie.student;

import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StudentMapper implements Function<StudentDTO, Student> {

    @Override
    public Student apply(StudentDTO studentDTO) {
        return Student.builder()
                .studentId(studentDTO.studentId())
                .firstName(studentDTO.firstName())
                .lastName(studentDTO.lastName())
                .email(studentDTO.email())
                .build();
    }
}
