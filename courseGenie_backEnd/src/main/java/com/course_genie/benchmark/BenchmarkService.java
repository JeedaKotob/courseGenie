package com.course_genie.benchmark;

import com.course_genie.assessment.Assessment;
import com.course_genie.assessment.AssessmentRepository;
import com.course_genie.clo.CLO;
import com.course_genie.clo.CLORepository;
import com.course_genie.grade.Grade;
import com.course_genie.grade.GradeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BenchmarkService {
    private final BenchmarkRepository benchmarkRepository;
    private final AssessmentRepository assessmentRepository;
    private final CLORepository cloRepository;
    private final GradeRepository gradeRepository;
    private final BenchmarkDTOMapper benchmarkDTOMapper;

    public BenchmarkService(BenchmarkRepository benchmarkRepository, AssessmentRepository assessmentRepository, CLORepository cloRepository, GradeRepository gradeRepository, BenchmarkDTOMapper benchmarkDTOMapper) {
        this.benchmarkRepository = benchmarkRepository;
        this.assessmentRepository = assessmentRepository;
        this.cloRepository = cloRepository;
        this.gradeRepository = gradeRepository;
        this.benchmarkDTOMapper = benchmarkDTOMapper;
    }

    public List<BenchmarkDTO> findAll() {
        return benchmarkRepository.findAll().stream().map(benchmarkDTOMapper).collect(Collectors.toList());
    }

    public List<Map<String, Object>> getBenchmarkResults(Long sectionId, Long bm1Id, Long bm2Id) {
        List<Map<String, Object>> results = new ArrayList<>();

        // Fetch all CLOs
        List<CLO> clos = cloRepository.findCLOBySectionId(sectionId).orElse(new ArrayList<>());

        // Fetch selected benchmarks
        Benchmark bm1 = benchmarkRepository.findById(bm1Id)
                .orElseThrow(() -> new EntityNotFoundException("Benchmark not found"));
        Benchmark bm2 = benchmarkRepository.findById(bm2Id)
                .orElseThrow(() -> new EntityNotFoundException("Benchmark not found"));

        for (CLO clo : clos) {
            Map<String, Object> result = new HashMap<>();
            result.put("CLO", clo.getName());
            result.put("Description", clo.getDescription());

            // Fetch assessments linked to this CLO
            List<Assessment> assessments = assessmentRepository.findAssessmentsByCloIdAndSectionId(clo.getCloId(), sectionId)
                    .orElse(new ArrayList<>());

            List<String> assessmentNames = new ArrayList<>();
            List<String> bm1Results = new ArrayList<>();
            List<String> bm2Results = new ArrayList<>();

            boolean bm1Met = false, bm2Met = false;

            for (Assessment assessment : assessments) {
                assessmentNames.add(assessment.getName());

                // Fetch grades for the assessment
                List<Grade> grades = gradeRepository.findGradeByAssessmentAssessmentId(assessment.getAssessmentId())
                        .orElse(new ArrayList<>());

                bm1Met |= calculateBenchmarkScore(bm1, grades, assessment, bm1Results);
                bm2Met |= calculateBenchmarkScore(bm2, grades, assessment, bm2Results);
            }

            result.put("Assessment_Instruments", String.join(", ", assessmentNames));
            result.put("Benchmark_Score", "<p>BM1 scores:<br>" + String.join("<br>", bm1Results) +
                    "<br>BM2 scores:<br>" + String.join("<br>", bm2Results) + "</p>");
            result.put("Result", (bm1Met && bm2Met) ? "MET" : (bm1Met || bm2Met) ? "Partially MET" : "NOT MET");

            results.add(result);
        }

        return results;
    }

    private boolean calculateBenchmarkScore(Benchmark benchmark, List<Grade> grades, Assessment assessment, List<String> resultList) {
        if (grades.isEmpty()) return false;

        double threshold = benchmark.getThreshold();
        String benchmarkType = benchmark.getBenchmarkType();

        if ("Grade".equals(benchmarkType)) {
            double thresholdScore = (threshold / 100.0) * assessment.getMaxPoints();
            long studentsAboveThreshold = grades.stream().filter(g -> g.getScore() >= thresholdScore).count();
            double percentage = (double) studentsAboveThreshold / grades.size() * 100;
            resultList.add(String.format("%.2f%% of students achieved %d%% or more in %s", percentage, (int) threshold, assessment.getName()));
            return percentage >= threshold;
        }

        if ("Mean Score".equals(benchmarkType)) {
            double meanScore = grades.stream().mapToDouble(Grade::getScore).average().orElse(0.0);
            resultList.add(String.format("Mean score in %s is %.0f%%", assessment.getName(), meanScore));
            return meanScore >= threshold;
        }

        return false;
    }

}