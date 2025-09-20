package com.course_genie.student;

import lombok.Builder;

@Builder
public record StudentDTO(
        String studentId,
        String firstName,
        String lastName,
        String email
) {
}
