package com.course_genie.section;

public record UpdateSectionRequest(
        String code,
        String semesterName,
        Long professorId
) {}