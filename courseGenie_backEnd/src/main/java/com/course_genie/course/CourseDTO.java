package com.course_genie.course;


import com.course_genie.clo.CLODTO;
import com.course_genie.section.SectionDTO;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Builder
public record CourseDTO(

        long courseId,

        String code,
        String name,
        String description,
        String credits,

        List<SectionDTO> sections,
        List<CLODTO> clos
) {
    public CourseDTO {
        if (sections == null) {
            sections = new ArrayList<>(); // Ensure it's never null
        }
        if (clos == null) {
            clos = new ArrayList<>(); // Ensure it's never null
        }
    }
}
