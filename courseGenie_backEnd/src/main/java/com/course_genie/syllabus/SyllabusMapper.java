package com.course_genie.syllabus;

import com.course_genie.course.CourseMapper;
import com.course_genie.section.Section;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SyllabusMapper implements Function<SyllabusDTO, Syllabus> {
    private final CourseMapper courseMapper;

    public SyllabusMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public Syllabus apply(SyllabusDTO syllabusDTO) {
        return Syllabus.builder()
                .syllabusId(syllabusDTO.syllabusId())
                .content(syllabusDTO.content())
                .section(new Section(syllabusDTO.sectionId()))
                .build();
    }
}
