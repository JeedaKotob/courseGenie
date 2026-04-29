package com.course_genie.benchmark;

import com.course_genie.assessment.Assessment;
import com.course_genie.assessment.AssessmentRepository;
import com.course_genie.car.Car;
import com.course_genie.car.CarRepository;
import com.course_genie.clo.CLO;
import com.course_genie.clo.CLORepository;
import com.course_genie.grade.Grade;
import com.course_genie.grade.GradeRepository;
import com.course_genie.section.Section;
import com.course_genie.section.SectionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class BenchmarkService {
    private static final Pattern CLO_NAME_SUFFIX_PATTERN = Pattern.compile("(\\d+)$");
    private final BenchmarkRepository benchmarkRepository;
    private final AssessmentRepository assessmentRepository;
    private final CLORepository cloRepository;
    private final GradeRepository gradeRepository;
    private final BenchmarkDTOMapper benchmarkDTOMapper;
    private final CarRepository carRepository;
    private final SectionRepository sectionRepository;

    public BenchmarkService(BenchmarkRepository benchmarkRepository, AssessmentRepository assessmentRepository, CLORepository cloRepository, GradeRepository gradeRepository, BenchmarkDTOMapper benchmarkDTOMapper, CarRepository carRepository, SectionRepository sectionRepository) {
        this.benchmarkRepository = benchmarkRepository;
        this.assessmentRepository = assessmentRepository;
        this.cloRepository = cloRepository;
        this.gradeRepository = gradeRepository;
        this.benchmarkDTOMapper = benchmarkDTOMapper;
        this.carRepository = carRepository;
        this.sectionRepository = sectionRepository;
    }

    public List<BenchmarkDTO> findAll() {
        return benchmarkRepository.findAll().stream().map(benchmarkDTOMapper).collect(Collectors.toList());
    }

    public List<Map<String, Object>> getBenchmarkResults(Long sectionId, Long bm1Id, Long bm2Id) {
        List<Map<String, Object>> results = new ArrayList<>();

        // Fetch all CLOs
        List<CLO> clos = cloRepository.findCLOBySectionId(sectionId).orElse(new ArrayList<>())
                .stream()
                .sorted(Comparator
                        .comparingInt((CLO clo) -> extractCloOrder(clo.getName()))
                        .thenComparing(clo -> clo.getName() == null ? "" : clo.getName(), String.CASE_INSENSITIVE_ORDER))
                .toList();

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
            result.put("Benchmark_Score", "<p><strong>BM1 scores:</strong><br>" + String.join("<br>", bm1Results) +
                    "<br><strong>BM2 scores:</strong><br>" + String.join("<br>", bm2Results) + "</p>");
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
            double meanRawScore = grades.stream().mapToDouble(Grade::getScore).average().orElse(0.0);
            double maxPoints = assessment.getMaxPoints();
            if (maxPoints <= 0) {
                resultList.add(String.format("Mean score in %s could not be computed (invalid max points)", assessment.getName()));
                return false;
            }
            double meanPercentage = (meanRawScore / maxPoints) * 100.0;
            resultList.add(String.format("Mean score in %s is %.2f%%", assessment.getName(), meanPercentage));
            return meanPercentage >= threshold;
        }

        return false;
    }

    private int extractCloOrder(String cloName) {
        if (cloName == null) {
            return Integer.MAX_VALUE;
        }
        Matcher matcher = CLO_NAME_SUFFIX_PATTERN.matcher(cloName.trim());
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return Integer.MAX_VALUE;
    }

    public String getCloBenchmarkReflection(Long sectionId) {
        return getOrCreateCar(sectionId).getCloBenchmarkReflection();
    }

    public String saveCloBenchmarkReflection(Long sectionId, String reflection) {
        Car car = getOrCreateCar(sectionId);
        car.setCloBenchmarkReflection(reflection == null ? null : reflection.trim());
        return carRepository.save(car).getCloBenchmarkReflection();
    }

    private Car getOrCreateCar(Long sectionId) {
        return carRepository.findCarBySectionSectionId(sectionId)
                .orElseGet(() -> {
                    Section section = sectionRepository.findById(sectionId)
                            .orElseThrow(() -> new EntityNotFoundException("Section not found"));
                    Car newCar = Car.builder()
                            .section(section)
                            .submitted(false)
                            .build();
                    return carRepository.save(newCar);
                });
    }

}