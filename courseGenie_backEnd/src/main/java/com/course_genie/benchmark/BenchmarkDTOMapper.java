package com.course_genie.benchmark;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BenchmarkDTOMapper implements Function<Benchmark, BenchmarkDTO> {
    @Override
    public BenchmarkDTO apply(Benchmark benchmark) {
        return BenchmarkDTO.builder()
                .benchmarkId(benchmark.getBenchmarkId())
                .benchmarkType(benchmark.getBenchmarkType())
                .description(benchmark.getDescription())
                .threshold(benchmark.getThreshold())
                .percentage(benchmark.getPercentage())
                .build();
    }
}
