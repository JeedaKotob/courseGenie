package com.course_genie.student;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;


    public Student(String studentId) {
        this.studentId = studentId;
    }
}
