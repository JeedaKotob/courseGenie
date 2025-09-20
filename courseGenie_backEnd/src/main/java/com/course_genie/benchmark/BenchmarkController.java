package com.course_genie.benchmark;

import com.course_genie.clo.CLODTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/benchmarks")
public class BenchmarkController {

    private final BenchmarkService benchmarkService;

    public BenchmarkController(BenchmarkService benchmarkService) {
        this.benchmarkService = benchmarkService;
    }

    @GetMapping()
    public ResponseEntity<List<BenchmarkDTO>> findAll() {
        return ResponseEntity.ok(benchmarkService.findAll());
    }

    @GetMapping(path = "section/{sectionId}/firstBm/{bm1Id}/secondBm/{bm2Id}")
    public ResponseEntity<List<Map<String, Object>>> getBenchmarkResults(@PathVariable("sectionId") Long sectionId, @PathVariable("bm1Id") Long bm1Id, @PathVariable("bm2Id") Long bm2Id) {
        return ResponseEntity.ok(benchmarkService.getBenchmarkResults(sectionId, bm1Id, bm2Id));
    }
}
