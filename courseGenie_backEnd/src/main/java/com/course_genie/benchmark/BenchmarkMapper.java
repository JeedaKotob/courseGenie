package com.course_genie.benchmark;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BenchmarkMapper implements Function<BenchmarkDTO, Benchmark> {
    @Override
    public Benchmark apply(BenchmarkDTO benchmarkDTO) {
        return Benchmark.builder()
                .benchmarkId(benchmarkDTO.benchmarkId())
                .benchmarkType(benchmarkDTO.benchmarkType())
                .description(benchmarkDTO.description())
                .threshold(benchmarkDTO.threshold())
                .percentage(benchmarkDTO.percentage())
                .build();
    }
}
