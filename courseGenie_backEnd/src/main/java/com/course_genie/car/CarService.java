package com.course_genie.car;

import com.course_genie.benchmark.Benchmark;
import com.course_genie.benchmark.BenchmarkRepository;
import com.course_genie.benchmark.BenchmarkService;
import com.course_genie.clo.CLO;
import com.course_genie.clo.CLORepository;
import com.course_genie.section.Section;
import com.course_genie.section.SectionRepository;
import com.course_genie.enrollment.Enrollment;
import com.course_genie.enrollment.EnrollmentRepository;
import com.course_genie.grade.Grade;
import com.course_genie.grade.GradeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;


import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CarService {
    private static final List<String> GRADE_ORDER = List.of("A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "F", "I", "W");
    private static final Pattern CLO_NAME_SUFFIX_PATTERN = Pattern.compile("(\\d+)$");

    private final CarRepository carRepository;
    private final SectionRepository sectionRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final GradeRepository gradeRepository;
    private final CLORepository cloRepository;
    private final BenchmarkRepository benchmarkRepository;
    private final BenchmarkService benchmarkService;
    private final CarDTOMapper carDTOMapper;
    private final SpringTemplateEngine templateEngine;

    public CarDTO getCarBySection(Long sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Section not found"));

        Car car = carRepository.findCarBySectionSectionId(sectionId)
                .orElseGet(() -> createEmptyCar(section));

        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentBySectionSectionId(sectionId);

        // Fix: Use .orElse(new ArrayList<>()) to handle the Optional return type
        List<Grade> allGrades = gradeRepository.findGradeByEnrollmentSectionSectionId(sectionId)
                .orElse(new ArrayList<>());

        Map<String, Integer> distribution = calculateGradeDistribution(enrollments, allGrades);
        double gpa = calculateSectionGpa(distribution);
        List<CloResultDTO> cloResults = new ArrayList<>();

        return carDTOMapper.toDto(car, section, enrollments, gpa, distribution, cloResults);
    }

    public CarDTO updateCarReflections(CarDTO dto) {
        Car car = carRepository.findById(dto.carId())
                .orElseThrow(() -> new EntityNotFoundException("CAR not found"));

        car.setStudentFeedbackSynopsis(dto.studentFeedbackSynopsis());
        car.setImpedimentsAnalysis(dto.impedimentsAnalysis());
        car.setSuggestedModifications(dto.suggestedModifications());
        car.setAiReflection(dto.aiReflection());

        Car savedCar = carRepository.save(car);
        return getCarBySection(savedCar.getSection().getSectionId());
    }

    public void submitCar(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("CAR not found"));

        if (!car.isSubmitted()) {
            car.setSubmitted(true);
            car.setSubmissionDate(LocalDate.now());
            carRepository.save(car);
        }
    }

    private Map<String, Integer> calculateGradeDistribution(List<Enrollment> enrollments, List<Grade> grades) {
        Map<String, Integer> distribution = new HashMap<>();
        GRADE_ORDER.forEach(g -> distribution.put(g, 0));

        Map<Long, Double> studentTotals = new HashMap<>();
        for (Grade g : grades) {
            studentTotals.merge(g.getEnrollment().getEnrollmentId(), g.getScore(), Double::sum);
        }

        for (Enrollment en : enrollments) {
            if (en.getStatus() == Enrollment.EnrollmentStatus.WITHDRAWN) {
                distribution.put("W", distribution.get("W") + 1);
            } else {
                double totalScore = studentTotals.getOrDefault(en.getEnrollmentId(), 0.0);
                String letter = convertToLetter(totalScore);
                distribution.put(letter, distribution.get(letter) + 1);
            }
        }
        return distribution;
    }

    private String convertToLetter(double score) {
        // Keep CAR buckets aligned with Statistics page distribution.
        if (score >= 94) return "A";
        if (score >= 90) return "A-";
        if (score >= 87) return "B+";
        if (score >= 83) return "B";
        if (score >= 80) return "B-";
        if (score >= 77) return "C+";
        if (score >= 73) return "C";
        if (score >= 70) return "C-";
        if (score >= 60) return "D";
        return "F";
    }

    private double calculateSectionGpa(Map<String, Integer> dist) {
        double points =
                (dist.getOrDefault("A", 0) * 4.0) +
                (dist.getOrDefault("A-", 0) * 3.67) +
                (dist.getOrDefault("B+", 0) * 3.33) +
                (dist.getOrDefault("B", 0) * 3.0) +
                (dist.getOrDefault("B-", 0) * 2.67) +
                (dist.getOrDefault("C+", 0) * 2.33) +
                (dist.getOrDefault("C", 0) * 2.0) +
                (dist.getOrDefault("C-", 0) * 1.67) +
                (dist.getOrDefault("D", 0) * 1.0) +
                (dist.getOrDefault("F", 0) * 0.0);

        int totalGraded =
                dist.getOrDefault("A", 0) +
                dist.getOrDefault("A-", 0) +
                dist.getOrDefault("B+", 0) +
                dist.getOrDefault("B", 0) +
                dist.getOrDefault("B-", 0) +
                dist.getOrDefault("C+", 0) +
                dist.getOrDefault("C", 0) +
                dist.getOrDefault("C-", 0) +
                dist.getOrDefault("D", 0) +
                dist.getOrDefault("F", 0);

        return totalGraded == 0 ? 0.0 : points / totalGraded;
    }

    private Car createEmptyCar(Section section) {
        Car newCar = Car.builder()
                .section(section)
                .submitted(false)
                .build();
        return carRepository.save(newCar);
    }

    public String generateCarHtml(Long sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Section not found"));
        CarDTO car = getCarBySection(sectionId);

        Context context = new Context();

        // Instructor and semester (same sources as SyllabusService.generateSyllabus)
        var professor = section.getProfessor();
        context.setVariable("instructorName", professor != null ? professor.getFullName() : "");
        context.setVariable("office", professor != null && professor.getOffice() != null ? professor.getOffice() : "");
        context.setVariable("officeHours", professor != null && professor.getOfficeHours() != null ? professor.getOfficeHours() : "");
        context.setVariable("phone", professor != null && professor.getPhone() != null ? professor.getPhone() : "");
        context.setVariable("email", professor != null && professor.getEmail() != null ? professor.getEmail() : "");

        String semesterYear = section.getSemester() != null && section.getSemester().getSemesterName() != null
                ? section.getSemester().getSemesterName()
                : "";
        context.setVariable("semesterYear", semesterYear);

        var course = section.getCourse();
        List<String> courseClos = cloRepository.findCLOByCourseCourseId(course.getCourseId())
                .orElse(List.of())
                .stream()
                .sorted(Comparator
                        .comparingInt((CLO clo) -> extractCloOrder(clo.getName()))
                        .thenComparing(clo -> clo.getName() == null ? "" : clo.getName(), String.CASE_INSENSITIVE_ORDER))
                .map(this::formatCloLine)
                .toList();
        context.setVariable("undergraduate", course.isUndergraduate() ? "Yes" : "No");
        context.setVariable("graduate", course.isGraduate() ? "Yes" : "No");
        context.setVariable("credits", course.getCredits() != null ? course.getCredits() : "");
        context.setVariable("prerequisites", course.getPrerequisites() != null ? course.getPrerequisites() : "");
        context.setVariable("corequisites", course.getCorequisites() != null ? course.getCorequisites() : "");
        context.setVariable("semesterCode", section.getCode() != null ? section.getCode() : "");
        context.setVariable("innovationJourneyCourse", car.designatedInnovationJourneyCourse() ? "Yes" : "No");

        context.setVariable("courseCode", car.courseCode());
        context.setVariable("courseTitle", car.courseTitle());
        context.setVariable("classGpa", String.format(Locale.US, "%.2f", car.classGpa()));
        context.setVariable("gradeDistribution", car.gradeDistribution());
        context.setVariable("gradeDistributionPercentages", calculateGradePercentages(car.gradeDistribution(), car.enrollment()));
        context.setVariable("cloResults", car.cloResults());
        context.setVariable("impedimentsAnalysis", car.impedimentsAnalysis());
        context.setVariable("suggestedModifications", car.suggestedModifications());
        context.setVariable("studentFeedback", car.studentFeedbackSynopsis());
        context.setVariable("logoUrl", "/static/images/logo.jpg");
        context.setVariable("enrollment", car.enrollment());
        context.setVariable("withdrawals", car.withdrawals());
        context.setVariable("courseClos", courseClos);
        context.setVariable("benchmarkDescriptions", getBenchmarkDescriptions());
        context.setVariable("carCloBenchmarkRows", getCarCloBenchmarkRows(sectionId));
        context.setVariable("cloBenchmarkReflection", carEntityReflection(sectionId));

        return templateEngine.process("car", context);
    }

    private Map<String, String> calculateGradePercentages(Map<String, Integer> distribution, int enrollment) {
        Map<String, String> percentages = new HashMap<>();
        for (String grade : GRADE_ORDER) {
            int count = distribution.getOrDefault(grade, 0);
            double percent = enrollment == 0 ? 0.0 : (count * 100.0) / enrollment;
            percentages.put(grade, String.format(Locale.US, "%.1f%%", percent));
        }
        return percentages;
    }

    private String formatCloLine(CLO clo) {
        String cloCode = (clo.getName() == null || clo.getName().isBlank())
                ? "CLO" + clo.getCloId()
                : clo.getName().trim();
        String cloText = clo.getDescription() == null ? "" : clo.getDescription().trim();
        return cloText.isBlank() ? cloCode : cloCode + ": " + cloText;
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

    private List<String> getBenchmarkDescriptions() {
        return benchmarkRepository.findAll()
                .stream()
                .sorted(Comparator.comparingLong(Benchmark::getBenchmarkId))
                .map(Benchmark::getDescription)
                .filter(desc -> desc != null && !desc.isBlank())
                .toList();
    }

    private List<Map<String, Object>> getCarCloBenchmarkRows(Long sectionId) {
        List<Benchmark> benchmarks = benchmarkRepository.findAll()
                .stream()
                .sorted(Comparator.comparingLong(Benchmark::getBenchmarkId))
                .toList();

        if (benchmarks.size() < 2) {
            return List.of();
        }

        return benchmarkService.getBenchmarkResults(
                sectionId,
                benchmarks.get(0).getBenchmarkId(),
                benchmarks.get(1).getBenchmarkId()
        );
    }

    private String carEntityReflection(Long sectionId) {
        return carRepository.findCarBySectionSectionId(sectionId)
                .map(Car::getCloBenchmarkReflection)
                .orElse("");
    }
}