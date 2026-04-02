package com.course_genie.car;

import com.course_genie.section.Section;
import com.course_genie.enrollment.Enrollment;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

@Service
public class CarDTOMapper {

    public CarDTO toDto(
            Car car,
            Section section,
            List<Enrollment> enrollments,
            double gpa,
            Map<String, Integer> distribution,
            List<CloResultDTO> cloResults) {

        int totalEnrollment = enrollments.size();
        int withdrawals = (int) enrollments.stream()
                .filter(e -> e.getStatus() == Enrollment.EnrollmentStatus.WITHDRAWN)
                .count();

        return new CarDTO(
                car.getCarId(),
                section.getSectionId(),
                section.getCourse().getCode(),
                section.getCourse().getName(),
                totalEnrollment,
                withdrawals,
                gpa,
                section.getCourse().getDesignatedInnovationJourneyCourse() != null && section.getCourse().getDesignatedInnovationJourneyCourse(),
                distribution,
                cloResults,
                car.getStudentFeedbackSynopsis(),
                car.getImpedimentsAnalysis(),
                car.getSuggestedModifications(),
                car.getAiReflection(),
                car.isSubmitted(),
                car.getSubmissionDate(),
                checkIfComplete(car)
        );
    }

    private boolean checkIfComplete(Car car) {
        return isNotNullOrEmpty(car.getStudentFeedbackSynopsis()) &&
                isNotNullOrEmpty(car.getImpedimentsAnalysis()) &&
                isNotNullOrEmpty(car.getSuggestedModifications());
    }

    private boolean isNotNullOrEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
}