package com.course_genie.car;

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


import java.util.*;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final SectionRepository sectionRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final GradeRepository gradeRepository;
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

    private Map<String, Integer> calculateGradeDistribution(List<Enrollment> enrollments, List<Grade> grades) {
        Map<String, Integer> distribution = new HashMap<>();
        List.of("A", "B", "C", "D", "F", "W").forEach(g -> distribution.put(g, 0));

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
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    private double calculateSectionGpa(Map<String, Integer> dist) {
        double points = (dist.get("A") * 4.0) + (dist.get("B") * 3.0) + (dist.get("C") * 2.0) + (dist.get("D") * 1.0);
        int totalGraded = dist.get("A") + dist.get("B") + dist.get("C") + dist.get("D") + dist.get("F");
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
        CarDTO car = getCarBySection(sectionId);

        Context context = new Context();

        context.setVariable("courseCode", car.courseCode());
        context.setVariable("courseTitle", car.courseTitle());
        context.setVariable("classGpa", car.classGpa());
        context.setVariable("gradeDistribution", car.gradeDistribution());
        context.setVariable("cloResults", car.cloResults());
        context.setVariable("impedimentsAnalysis", car.impedimentsAnalysis());
        context.setVariable("suggestedModifications", car.suggestedModifications());
        context.setVariable("studentFeedback", car.studentFeedbackSynopsis());
        context.setVariable("logoUrl", "/static/images/logo.jpg");
        context.setVariable("enrollment", car.enrollment());
        context.setVariable("withdrawals", car.withdrawals());
        context.setVariable("innovationCourse", car.designatedInnovationJourneyCourse());

        return templateEngine.process("car", context);
    }
}