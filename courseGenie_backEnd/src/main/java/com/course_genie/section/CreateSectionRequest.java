package com.course_genie.section;

public record CreateSectionRequest(
        String code,
        String term,
        Long professorId
) {}