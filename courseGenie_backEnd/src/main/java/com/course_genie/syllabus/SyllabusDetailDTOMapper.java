package com.course_genie.syllabus;

import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Service
public class SyllabusDetailDTOMapper implements Function<Syllabus, SyllabusDetailDTO> {
    @Override
    public SyllabusDetailDTO apply(Syllabus syllabus) {

        LocalDate syllabusDueDate = syllabus.getSection()
                .getSemester()
                .getSyllabusDueDate();

        LocalDate comparisonDate = syllabus.isSubmitted()
                ? syllabus.getSubmissionDate()
                : LocalDate.now();

        long overdueBy = 0;

        if (comparisonDate != null && comparisonDate.isAfter(syllabusDueDate)) {
            overdueBy = ChronoUnit.DAYS.between(syllabusDueDate, comparisonDate);
        }

        return SyllabusDetailDTO.builder()
                .sectionId(syllabus.getSection().getSectionId())
                .courseName(syllabus.getSection().getCourse().getName())
                .courseCode(syllabus.getSection().getCourse().getCode())
                .sectionCode(syllabus.getSection().getCode())
                .submitted(syllabus.isSubmitted())
                .submissionDate(syllabus.getSubmissionDate())
                .syllabusDueDate(syllabusDueDate)
                .overdueBy(overdueBy)
                .build();
    }
}
