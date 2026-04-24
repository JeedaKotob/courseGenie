package com.course_genie.course;

import java.util.List;

public record CourseCollaboratorDTO(
        Long professorId,
        String professorName,
        String professorEmail,
        List<String> sectionCodes
) {
}
