package com.course_genie.benchmark;

import lombok.Builder;

@Builder
public record BenchmarkDTO(
         long benchmarkId,
         String benchmarkType,
         String description,
         int threshold,
         int percentage
) {
}
