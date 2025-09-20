package com.course_genie.benchmark;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BenchmarkRepository extends JpaRepository<Benchmark, Long> {
}
