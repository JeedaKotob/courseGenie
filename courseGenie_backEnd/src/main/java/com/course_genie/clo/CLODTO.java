package com.course_genie.clo;

import lombok.Builder;

@Builder
public record CLODTO(
        long cloId,
        String name,
        String description,
        long courseId

) {
}
