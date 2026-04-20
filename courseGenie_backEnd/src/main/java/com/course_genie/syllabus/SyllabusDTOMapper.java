package com.course_genie.syllabus;

import com.course_genie.course.CourseDTOMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

@Service
public class SyllabusDTOMapper implements Function<Syllabus, SyllabusDTO> {

    private final CourseDTOMapper courseDTOMapper;

    public SyllabusDTOMapper(CourseDTOMapper courseDTOMapper) {
        this.courseDTOMapper = courseDTOMapper;
    }

    @Override
    public SyllabusDTO apply(Syllabus syllabus) {
        long overdueBy = 0;
        LocalDate syllabusDueDate = syllabus.getSection().getSemester().getSyllabusDueDate();
        LocalDate comparisonDate = syllabus.isSubmitted()
                ? syllabus.getSubmissionDate()
                : LocalDate.now();

        if (comparisonDate != null && comparisonDate.isAfter(syllabusDueDate)) {
            overdueBy = ChronoUnit.DAYS.between(syllabusDueDate, comparisonDate);
        }

        return SyllabusDTO.builder()
                .syllabusId(syllabus.getSyllabusId())
                .content(syllabus.getContent())
                .sectionId(syllabus.getSection().getSectionId())
                .submitted(syllabus.isSubmitted())
                .syllabusDueDate(syllabusDueDate)
                .overdueBy(overdueBy)
                .build();
    }
}
