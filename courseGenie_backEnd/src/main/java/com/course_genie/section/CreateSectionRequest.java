package com.course_genie.section;

public record CreateSectionRequest(
        String code,
        String semesterName,
        Long professorId
) {}