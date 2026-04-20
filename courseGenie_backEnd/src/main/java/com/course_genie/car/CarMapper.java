package com.course_genie.car;

import com.course_genie.section.Section;
import org.springframework.stereotype.Service;

@Service
public class CarMapper {

    public Car toEntity(CarDTO dto, Section section) {
        return Car.builder()
                .carId(dto.carId())
                .section(section)
                .studentFeedbackSynopsis(dto.studentFeedbackSynopsis())
                .impedimentsAnalysis(dto.impedimentsAnalysis())
                .suggestedModifications(dto.suggestedModifications())
                .aiReflection(dto.aiReflection())
                .submitted(dto.submitted())
                .submissionDate(dto.submissionDate())
                .build();
    }
}