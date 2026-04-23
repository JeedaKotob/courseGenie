package com.course_genie.car;

import lombok.Builder;

import java.util.List;

@Builder
public record CarProgressDTO(
        long professorId,
        String professorName,
        String departmentName,
        int totalSections,
        int submittedCars,
        double progressPercentage,
        List<CarDetailDTO> sections
) {}
