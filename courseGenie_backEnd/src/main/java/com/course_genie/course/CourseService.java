package com.course_genie.course;

import com.course_genie.assessment.AssessmentDTO;
import com.course_genie.assessment.AssessmentDTOMapper;
import com.course_genie.assessment.AssessmentRepository;
import com.course_genie.clo.CLO;
import com.course_genie.clo.CLODTO;
import com.course_genie.clo.CLODTOMapper;
import com.course_genie.clo.CLORepository;
import com.course_genie.section.Section;
import com.course_genie.section.SectionDTO;
import com.course_genie.section.SectionDTOMapper;
import com.course_genie.section.SectionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CourseDTOMapper courseDTOMapper;
    private final SectionRepository sectionRepository;
    private final SectionDTOMapper sectionDTOMapper;
    private final AssessmentRepository assessmentRepository;
    private final AssessmentDTOMapper assessmentDTOMapper;
    private final CLORepository cloRepository;
    private final CLODTOMapper cloDTOMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper, CourseDTOMapper courseDTOMapper, SectionRepository sectionRepository, SectionDTOMapper sectionDTOMapper, AssessmentRepository assessmentRepository, AssessmentDTOMapper assessmentDTOMapper, CLORepository cloRepository, CLODTOMapper cloDTOMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.courseDTOMapper = courseDTOMapper;
        this.sectionRepository = sectionRepository;
        this.sectionDTOMapper = sectionDTOMapper;
        this.assessmentRepository = assessmentRepository;
        this.assessmentDTOMapper = assessmentDTOMapper;
        this.cloRepository = cloRepository;
        this.cloDTOMapper = cloDTOMapper;
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseMapper.apply(courseDTO);
        return courseDTOMapper.apply(courseRepository.save(course));
    }

    //Does not work when required section not performing as required on the frontend side need to debug
    public Map<String, Set<CourseDTO>> getAllCourses() {
        List<CourseDTO> courseDTOList = courseRepository.findAll()
                .stream()
                .map(courseDTOMapper)
                .toList();

        for (CourseDTO courseDTO : courseDTOList) {
            List<SectionDTO> sectionDTOS = sectionRepository.findByCourseCourseId(courseDTO.courseId())
                    .orElse(new ArrayList<>())
                    .stream()
                    .map(sectionDTOMapper)
                    .toList();
            courseDTO.sections().addAll(sectionDTOS);
        }

        // Group courses by term(s) from their sections.
        Map<String, Set<CourseDTO>> groupedByTerm = new HashMap<>();
        for (CourseDTO courseDTO : courseDTOList) {
            // Collect all distinct term values for this course from its sections.
            Set<String> terms = courseDTO.sections().stream()
                    .map(SectionDTO::term)
                    .filter(term -> term != null && !term.isEmpty())
                    .collect(Collectors.toSet());
            if (terms.isEmpty()) {
                terms.add("Unknown Term");
            }
            // Add the course to each term group it belongs to.
            for (String term : terms) {
                groupedByTerm.computeIfAbsent(term, k -> new HashSet<>()).add(courseDTO);
            }
        }

        return groupedByTerm;
    }

    /**
     * Returns a flat list of all courses in the database as CourseDTOs.
     */
    public List<CourseDTO> getAllCourse() {
        return courseRepository.findAll()
                .stream()
                .map(courseDTOMapper)
                .collect(Collectors.toList());

    }

    public CourseDTO getCourseByCodeAndSectionCode(String courseCode, String sectionCode) {
        Course course = courseRepository.findCourseByCode(courseCode).orElseThrow(() -> new EntityNotFoundException("Course not found"));
        CourseDTO courseDTO = courseDTOMapper.apply(course);
        Section section = sectionRepository.findSectionByCodeAndCourseCode(sectionCode, courseCode).orElseThrow(() -> new EntityNotFoundException("Section not found"));
        SectionDTO sectionDTO = sectionDTOMapper.apply(section);
        List<AssessmentDTO> assessmentDTOS = assessmentRepository.findAssessmentBySectionSectionId(sectionDTO.sectionId()).orElse(new ArrayList<>())
                .stream().map(assessmentDTOMapper).toList();
        sectionDTO.assessments().addAll(assessmentDTOS);
        courseDTO.sections().add(sectionDTO);
        List<CLODTO> closDTO = cloRepository.findCLOByCourseCourseId(course.getCourseId()).orElseThrow(() -> new EntityNotFoundException("CLO not found")).stream().map(cloDTOMapper).toList();
        courseDTO.clos().addAll(closDTO);
        return courseDTO;
    }

    public Map<String, Set<CourseDTO>> getCoursesByProfessorId(Long professorId) {
        // Fetch courses for the given professor and map them to CourseDTO objects.
        List<CourseDTO> courseDTOList = courseRepository.findCourseByProfessorId(professorId)
                .stream()
                .map(courseDTOMapper)
                .toList();

        System.out.println("Fetched " + courseDTOList.size() + " CourseDTOs for professorId: " + professorId);

        // For each course, fetch its sections and add them to the courseDTO.
        for (CourseDTO courseDTO : courseDTOList) {
            List<SectionDTO> sectionDTOS = sectionRepository
                    .findByCourseCourseIdAndProfessorUserId(courseDTO.courseId(), professorId)
                    .orElse(new ArrayList<>())
                    .stream()
                    .map(sectionDTOMapper)
                    .toList();
            // Add all fetched sections to the courseDTO's section list.
            courseDTO.sections().addAll(sectionDTOS);
            System.out.println("Course ID: " + courseDTO.courseId() + " has " + sectionDTOS.size() + " sections.");
        }

        // Group courses by term from their sections.
        // If a course has sections in multiple terms, we clone the course for each term,
        // keeping only the sections that belong to that term.
        Map<String, Set<CourseDTO>> groupedByTerm = new HashMap<>();
        for (CourseDTO courseDTO : courseDTOList) {
            // Extract all distinct terms from the course's sections.
            Set<String> terms = courseDTO.sections().stream()
                    .map(SectionDTO::term)
                    .filter(term -> term != null && !term.isEmpty())
                    .collect(Collectors.toSet());
            if (terms.isEmpty()) {
                terms.add("Unknown Term");
            }
            System.out.println("Course ID: " + courseDTO.courseId() + " distinct terms: " + terms);
            // For each term, clone the CourseDTO to include only sections with that term.
            for (String term : terms) {
                CourseDTO filteredCourse = cloneCourseForTerm(courseDTO, term);
                groupedByTerm.computeIfAbsent(term, k -> new HashSet<>()).add(filteredCourse);
            }
        }

        // Debug: Print out the grouped map.
        System.out.println("Grouped courses by term:");
        groupedByTerm.forEach((term, courses) -> {
            System.out.println("Term: " + term);
            courses.forEach(course -> {
                System.out.println("  Course ID: " + course.courseId() +
                        ", Code: " + course.code() +
                        ", Name: " + course.name() +
                        ", Sections: " + course.sections());
            });
        });

        return groupedByTerm;
    }

    private CourseDTO cloneCourseForTerm(CourseDTO course, String term) {
        // Filter sections to include only those with the matching term.
        List<SectionDTO> filteredSections = course.sections().stream()
                .filter(section -> term.equals(section.term()))
                .collect(Collectors.toList());
        // Build a new CourseDTO using the builder provided by your record.
        return CourseDTO.builder()
                .courseId(course.courseId())
                .code(course.code())
                .name(course.name())
                .description(course.description())
                .credits(course.credits())
                .sections(new ArrayList<>(filteredSections))
                .clos(new ArrayList<>(course.clos()))
                .build();
    }
}
