package com.course_genie.enrollment;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EnrollmentDTOMapper implements Function<Enrollment, EnrollmentDTO> {

    @Override
    public EnrollmentDTO apply(Enrollment enrollment) {
        return new EnrollmentDTO(
                enrollment.getEnrollmentId(),
                enrollment.getStudent().getStudentId(),
                enrollment.getStudent().getFirstName(),
                enrollment.getStudent().getLastName(),
                enrollment.getStudent().getEmail(),
                enrollment.getSection().getSectionId()
        );
    }
}
