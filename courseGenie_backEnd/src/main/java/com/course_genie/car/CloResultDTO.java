package com.course_genie.car;

public record CloResultDTO(
        long cloId,
        String name,
        String description,
        String assessmentMethods,
        int benchmarkThreshold,
        double actualResult,
        boolean met
) {}