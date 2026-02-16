package com.course_genie.enrollment;
import lombok.Builder;

@Builder
public record EnrollmentDTO (
    long enrollmentId,
    String studentId,
    String firstName,
    String lastName,
    String email,
    long sectionId
){}
