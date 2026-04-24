package com.course_genie.course;

import com.course_genie.section.CreateSectionRequest;
import com.course_genie.section.UpdateSectionRequest;
import com.course_genie.user.User;
import com.course_genie.user.UserRepository;
import com.course_genie.assessment.AssessmentDTO;
import com.course_genie.assessment.AssessmentDTOMapper;
import com.course_genie.assessment.AssessmentRepository;
import com.course_genie.clo.CLODTO;
import com.course_genie.clo.CLODTOMapper;
import com.course_genie.clo.CLORepository;
import com.course_genie.semester.Semester;
import com.course_genie.semester.SemesterRepository;
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
    private final UserRepository userRepository;
    private final SemesterRepository semesterRepository;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper, CourseDTOMapper courseDTOMapper, SectionRepository sectionRepository, SectionDTOMapper sectionDTOMapper, AssessmentRepository assessmentRepository, AssessmentDTOMapper assessmentDTOMapper, CLORepository cloRepository, CLODTOMapper cloDTOMapper, UserRepository userRepository, SemesterRepository semesterRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.courseDTOMapper = courseDTOMapper;
        this.sectionRepository = sectionRepository;
        this.sectionDTOMapper = sectionDTOMapper;
        this.assessmentRepository = assessmentRepository;
        this.assessmentDTOMapper = assessmentDTOMapper;
        this.cloRepository = cloRepository;
        this.cloDTOMapper = cloDTOMapper;
        this.userRepository = userRepository;
        this.semesterRepository = semesterRepository;
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

        // Group courses by semester from their sections.
        Map<String, Set<CourseDTO>> groupedBySemester = new HashMap<>();
        for (CourseDTO courseDTO : courseDTOList) {
            Set<String> semesterNames = courseDTO.sections().stream()
                    .map(SectionDTO::semesterName)
                    .filter(semesterName -> semesterName != null && !semesterName.isEmpty())
                    .collect(Collectors.toSet());
            if (semesterNames.isEmpty()) {
                semesterNames.add("Unknown Semester");
            }
            for (String semesterName : semesterNames) {
                groupedBySemester.computeIfAbsent(semesterName, k -> new HashSet<>()).add(courseDTO);
            }
        }

        return groupedBySemester;
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

    public List<CourseCollaboratorDTO> getCourseCollaboratorsBySection(String courseCode, String sectionCode) {
        Section currentSection = sectionRepository
                .findSectionByCodeAndCourseCode(sectionCode, courseCode)
                .orElseThrow(() -> new EntityNotFoundException("Section not found"));

        Long semesterId = currentSection.getSemester() != null ? currentSection.getSemester().getSemesterId() : null;
        if (semesterId == null) {
            return new ArrayList<>();
        }

        Long currentProfessorId = currentSection.getProfessor() != null ? currentSection.getProfessor().getUserId() : null;

        List<Section> sameCourseSameSemesterSections = sectionRepository
                .findByCourseCodeAndSemesterSemesterId(courseCode, semesterId)
                .orElse(new ArrayList<>());

        Map<Long, List<Section>> sectionsByProfessor = sameCourseSameSemesterSections.stream()
                .filter(section -> section.getProfessor() != null)
                .filter(section -> currentProfessorId == null || !Objects.equals(section.getProfessor().getUserId(), currentProfessorId))
                .collect(Collectors.groupingBy(section -> section.getProfessor().getUserId()));

        List<CourseCollaboratorDTO> collaborators = new ArrayList<>();
        for (Map.Entry<Long, List<Section>> entry : sectionsByProfessor.entrySet()) {
            List<Section> professorSections = entry.getValue();
            if (professorSections.isEmpty()) {
                continue;
            }

            User professor = professorSections.get(0).getProfessor();
            List<String> sectionCodes = professorSections.stream()
                    .map(Section::getCode)
                    .filter(Objects::nonNull)
                    .sorted()
                    .toList();

            collaborators.add(new CourseCollaboratorDTO(
                    professor.getUserId(),
                    professor.getFullName(),
                    professor.getEmail(),
                    sectionCodes
            ));
        }

        collaborators.sort(Comparator.comparing(CourseCollaboratorDTO::professorName, String.CASE_INSENSITIVE_ORDER));
        return collaborators;
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

        // Group courses by semester from their sections.
        // If a course has sections in multiple semesters, we clone the course for each semester,
        // keeping only the sections that belong to that term.
        Map<String, Set<CourseDTO>> groupedBySemester = new HashMap<>();
        for (CourseDTO courseDTO : courseDTOList) {
            Set<String> semesterNames = courseDTO.sections().stream()
                    .map(SectionDTO::semesterName)
                    .filter(semesterName -> semesterName != null && !semesterName.isEmpty())
                    .collect(Collectors.toSet());
            if (semesterNames.isEmpty()) {
                semesterNames.add("Unknown Semester");
            }
            System.out.println("Course ID: " + courseDTO.courseId() + " distinct semesters: " + semesterNames);
            for (String semesterName : semesterNames) {
                CourseDTO filteredCourse = cloneCourseForSemester(courseDTO, semesterName);
                groupedBySemester.computeIfAbsent(semesterName, k -> new HashSet<>()).add(filteredCourse);
            }
        }

        System.out.println("Grouped courses by semester:");
        groupedBySemester.forEach((semesterName, courses) -> {
            System.out.println("Semester: " + semesterName);
            courses.forEach(course -> {
                System.out.println("  Course ID: " + course.courseId() +
                        ", Code: " + course.code() +
                        ", Name: " + course.name() +
                        ", Sections: " + course.sections());
            });
        });

        return groupedBySemester;
    }

    private CourseDTO cloneCourseForSemester(CourseDTO course, String semesterName) {
        List<SectionDTO> filteredSections = course.sections().stream()
                .filter(section -> semesterName.equals(section.semesterName()))
                .collect(Collectors.toList());
        return CourseDTO.builder()
                .courseId(course.courseId())
                .code(course.code())
                .name(course.name())
                .description(course.description())
                .credits(course.credits())
                .departmentId(course.departmentId())
                .departmentName(course.departmentName())
                .discipline(course.discipline())
                .sections(new ArrayList<>(filteredSections))
                .clos(new ArrayList<>(course.clos()))
                .build();
    }

    public CourseDTO getCourseByCode(String courseCode) {
        Course course = courseRepository.findCourseByCode(courseCode)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        CourseDTO courseDTO = courseDTOMapper.apply(course);

        // add all sections for this course
        List<SectionDTO> sectionDTOS = sectionRepository.findByCourseCourseId(course.getCourseId())
                .orElse(new ArrayList<>())
                .stream()
                .map(sectionDTOMapper)
                .toList();
        courseDTO.sections().addAll(sectionDTOS);

        // add CLOs for this course
        List<CLODTO> closDTO = cloRepository.findCLOByCourseCourseId(course.getCourseId())
                .orElse(new ArrayList<>())
                .stream()
                .map(cloDTOMapper)
                .toList();
        courseDTO.clos().addAll(closDTO);

        return courseDTO;
    }

    public SectionDTO createSection(String courseCode, CreateSectionRequest request) {
        Course course = courseRepository.findCourseByCode(courseCode)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
    
        if (request.code() == null || request.code().isBlank()) {
            throw new IllegalArgumentException("Section code is required");
        }
        if (request.semesterName() == null || request.semesterName().isBlank()) {
            throw new IllegalArgumentException("Semester is required");
        }
        if (request.professorId() == null) {
            throw new IllegalArgumentException("Professor is required");
        }

        String normalizedCode = request.code().trim();
    
        sectionRepository.findSectionByCodeAndCourseCode(normalizedCode, courseCode)
                .ifPresent(s -> {
                    throw new IllegalArgumentException("Section already exists for this course");
                });
    
        User professor = userRepository.findById(request.professorId())
                .orElseThrow(() -> new EntityNotFoundException("Professor not found"));

        Semester semester = semesterRepository.findBySemesterNameIgnoreCase(request.semesterName().trim())
                .orElseThrow(() -> new EntityNotFoundException("Semester not found"));
    
        Section section = Section.builder()
                .code(normalizedCode)
                .semester(semester)
                .class_number(normalizedCode)
                .configured(false)
                .course(course)
                .professor(professor)
                .build();
    
        Section saved = sectionRepository.save(section);
        return sectionDTOMapper.apply(saved);
    }

    public SectionDTO updateSection(String courseCode, Long sectionId, UpdateSectionRequest request) {
        courseRepository.findCourseByCode(courseCode)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
    
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Section not found"));
    
        if (section.getCourse() == null || !courseCode.equals(section.getCourse().getCode())) {
            throw new IllegalArgumentException("Section does not belong to the specified course");
        }
    
        if (request.code() == null || request.code().isBlank()) {
            throw new IllegalArgumentException("Section code is required");
        }
        if (request.semesterName() == null || request.semesterName().isBlank()) {
            throw new IllegalArgumentException("Semester is required");
        }
        if (request.professorId() == null) {
            throw new IllegalArgumentException("Professor is required");
        }
    
        String normalizedCode = request.code().trim();
        String normalizedSemesterName = request.semesterName().trim();
    
        sectionRepository.findSectionByCodeAndCourseCode(normalizedCode, courseCode)
                .ifPresent(existing -> {
                    if (existing.getSectionId() != sectionId) {
                        throw new IllegalArgumentException("Section already exists for this course");
                    }
                });
    
        User professor = userRepository.findById(request.professorId())
                .orElseThrow(() -> new EntityNotFoundException("Professor not found"));

        Semester semester = semesterRepository.findBySemesterNameIgnoreCase(normalizedSemesterName)
                .orElseThrow(() -> new EntityNotFoundException("Semester not found"));
    
        section.setCode(normalizedCode);
        section.setClass_number(normalizedCode);
        section.setSemester(semester);
        section.setProfessor(professor);
    
        Section saved = sectionRepository.save(section);
        return sectionDTOMapper.apply(saved);
    }
    
    public void deleteSection(String courseCode, Long sectionId) {
        courseRepository.findCourseByCode(courseCode)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
    
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Section not found"));
    
        if (section.getCourse() == null || !courseCode.equals(section.getCourse().getCode())) {
            throw new IllegalArgumentException("Section does not belong to the specified course");
        }
    
        sectionRepository.delete(section);
    }
}
