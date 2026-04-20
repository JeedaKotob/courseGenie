package com.course_genie.car;

import java.time.LocalDate;
import java.util.Map;
import java.util.List;

public record CarDTO(
        Long carId,
        Long sectionId,
        String courseCode,
        String courseTitle,
        int enrollment,
        int withdrawals,
        double classGpa,
        boolean designatedInnovationJourneyCourse,
        Map<String, Integer> gradeDistribution,
        List<CloResultDTO> cloResults,
        String studentFeedbackSynopsis,
        String impedimentsAnalysis,
        String suggestedModifications,
        String aiReflection,
        boolean submitted,
        LocalDate submissionDate,
        boolean isComplete
) {}