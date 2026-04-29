package com.course_genie.benchmark;

import com.course_genie.clo.CLODTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

    @GetMapping(path = "section/{sectionId}/reflection")
    public ResponseEntity<Map<String, String>> getBenchmarkReflection(@PathVariable("sectionId") Long sectionId) {
        Map<String, String> response = new HashMap<>();
        response.put("reflection", benchmarkService.getCloBenchmarkReflection(sectionId));
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "section/{sectionId}/reflection")
    public ResponseEntity<Map<String, String>> updateBenchmarkReflection(
            @PathVariable("sectionId") Long sectionId,
            @RequestBody Map<String, String> request
    ) {
        Map<String, String> response = new HashMap<>();
        response.put("reflection", benchmarkService.saveCloBenchmarkReflection(sectionId, request.get("reflection")));
        return ResponseEntity.ok(response);
    }
}
