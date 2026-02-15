package com.course_genie.syllabus;

import com.course_genie.assessment.CategoryDescriptionRepository;
import com.course_genie.assessment.CategoryDescription;

import com.course_genie.assessment.Assessment;
import com.course_genie.assessment.AssessmentRepository;
import com.course_genie.booksection.BookSection;
import com.course_genie.booksection.*;
import com.course_genie.booksection.BookSectionService;
import com.course_genie.clo.CLO;
import com.course_genie.clo.CLORepository;
import com.course_genie.course.CourseRepository;
import com.course_genie.section.Section;
import com.course_genie.section.SectionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.util.Collections;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class SyllabusService {

    private final SyllabusRepository syllabusRepository;
    private final SyllabusMapper syllabusMapper;
    private final SyllabusDTOMapper syllabusDTOMapper;
    private final CourseRepository courseRepository;
    private final CLORepository cloRepository;
    private final SectionRepository sectionRepository;
    private final AssessmentRepository assessmentRepository;
    private final SpringTemplateEngine templateEngine;
    private final BookSectionService bookSectionService;
    private final CategoryDescriptionRepository categoryDescriptionRepository;

    public SyllabusService(
            SyllabusRepository syllabusRepository,
            SyllabusMapper syllabusMapper,
            SyllabusDTOMapper syllabusDTOMapper,
            CourseRepository courseRepository,
            CLORepository cloRepository,
            SectionRepository sectionRepository,
            AssessmentRepository assessmentRepository,
            CategoryDescriptionRepository categoryDescriptionRepository,
            SpringTemplateEngine templateEngine,
            BookSectionService bookSectionService
    ) {
        this.syllabusRepository = syllabusRepository;
        this.syllabusMapper = syllabusMapper;
        this.syllabusDTOMapper = syllabusDTOMapper;
        this.courseRepository = courseRepository;
        this.cloRepository = cloRepository;
        this.sectionRepository = sectionRepository;
        this.assessmentRepository = assessmentRepository;
        this.categoryDescriptionRepository = categoryDescriptionRepository;
        this.templateEngine = templateEngine;
        this.bookSectionService = bookSectionService;
    }

    // Create
    public SyllabusDTO createSyllabus(SyllabusDTO syllabusDTO) {
        return syllabusDTOMapper.apply(syllabusRepository.save(syllabusMapper.apply(syllabusDTO)));
    }

    // Read
    public List<SyllabusDTO> getAllSyllabuses() {
        return syllabusRepository.findAll().stream().map(syllabusDTOMapper).collect(Collectors.toList());
    }

    public SyllabusDTO getSyllabusById(long syllabusId) {
        return syllabusDTOMapper.apply(syllabusRepository.findById(syllabusId).orElseThrow(() -> new EntityNotFoundException("Syllabus not found")));
    }

    // Update
    public SyllabusDTO updateSyllabus(SyllabusDTO syllabusDetails) {
        Syllabus syllabus = syllabusRepository.findById(syllabusDetails.syllabusId()).orElseThrow(() -> new EntityNotFoundException("Syllabus not found"));
        syllabus.setContent(syllabusDetails.content());
        return syllabusDTOMapper.apply(syllabusRepository.save(syllabus));
    }

    // Delete
    public void deleteSyllabus(long syllabusId) {
        Syllabus syllabus = syllabusRepository.findById(syllabusId).orElseThrow(() -> new EntityNotFoundException("Syllabus not found"));
        syllabusRepository.delete(syllabus);
    }

    // Generate Syllabus Automatically
    public SyllabusDTO generateSyllabus(long sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        // Create a Thymeleaf Context and populate it with variables
        Context context = new Context();
        context.setVariable("logoUrl", "/static/images/logo.jpg");
        context.setVariable("courseCode", section.getCourse().getCode());
        context.setVariable("courseTitle", section.getCourse().getName());
        context.setVariable("courseNumber", section.getCourse().getCode());
        context.setVariable("courseTitleFull", section.getCourse().getCode() + ": " + section.getCourse().getName());
        context.setVariable("courseSchedule", section.getSchedule() + ": Room " + section.getRoom()); // Replace with real value as needed
        context.setVariable("instructorName", section.getProfessor().getFirstName() + " " + section.getProfessor().getLastName());
        context.setVariable("office", section.getProfessor().getOffice());
        context.setVariable("officeHours", section.getProfessor().getOfficeHours());
        context.setVariable("phone", section.getProfessor().getPhone());
        context.setVariable("email", section.getProfessor().getEmail());
        context.setVariable("semesterYear", section.getTerm());
        context.setVariable("discipline", section.getCourse().getDiscipline());
        context.setVariable("courseApprovalDate", section.getCourse().getCourseApprovalDate());
        context.setVariable("lastRevisionDate", section.getCourse().getLastRevisionDate());
        context.setVariable("undergraduate", section.getCourse().isUndergraduate() ? "Yes" : "No");
        context.setVariable("newCourse", section.getCourse().isNewCourse() ? "Yes" : "No");
        context.setVariable("credits", section.getCourse().getCredits());
        context.setVariable("graduate", section.getCourse().isGraduate() ? "Yes" : "No");
        context.setVariable("prerequisites", section.getCourse().getPrerequisites());
        context.setVariable("corequisites", section.getCourse().getCorequisites());
        context.setVariable("courseDescription", section.getCourse().getDescription());
        context.setVariable("teachingMethodologies", section.getTeachingMethodology());
// ---- Category descriptions (e.g., "Quiz" in bold) ----
        List<CategoryDescription> categoryDescriptions = Optional
                .ofNullable(categoryDescriptionRepository.findBySection_SectionId(section.getSectionId()))
                .orElse(Collections.emptyList());

        String categoryDescriptionsHtml = categoryDescriptions.stream()
                .map(cd -> "<b>" + cd.getCategoryName() + ":</b> " + cd.getDescription())
                .collect(Collectors.joining("<br/>"));

        context.setVariable("categoryDescriptions", categoryDescriptionsHtml);
        List<CLO> cloList = cloRepository.findCLOBySectionId(section.getSectionId()).orElse(null);
        assert cloList != null;
        String cloHtml = cloList.stream()
                .sorted(Comparator.comparingInt(clo -> Integer.parseInt(clo.getName())))
                .map(clo -> "CLO - " + clo.getName() + ": " + clo.getDescription())
                .collect(Collectors.joining("<br/>")); // Using <br/> to separate each item

        context.setVariable("courseLearningOutcomesStr", cloHtml);
        // Creating course calendar mapping with week and assessments

// Build the unsorted courseCalendar as before.
        Map<String, List<Assessment>> courseCalendar = new HashMap<>();
        List<Assessment> assessments = assessmentRepository.findAssessmentBySectionSectionId(section.getSectionId())
                .orElse(Collections.emptyList());
        assessments.forEach(assessment -> {
            // Use "Unscheduled" if week is 0, else "Week X"
            String week = assessment.getWeek() == 0 ? "Unscheduled" : "Week " + assessment.getWeek();
            courseCalendar.computeIfAbsent(week, k -> new ArrayList<>()).add(assessment);
        });

// Ensure that Weeks 1 to 14 are present even if they have no assessments.
        for (int i = 1; i <= 14; i++) {
            String key = "Week " + i;
            courseCalendar.putIfAbsent(key, new ArrayList<>());
        }

// Now sort the map entries so that the weeks are ordered numerically and "Unscheduled" comes last.
        Map<String, List<Assessment>> sortedCalendar = courseCalendar.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(entry -> {
                    String key = entry.getKey();
                    // Give "Unscheduled" a very high value so it appears after the numbered weeks.
                    if ("Unscheduled".equalsIgnoreCase(key)) {
                        return Integer.MAX_VALUE;
                    }
                    // Remove "Week " to parse the numeric part.
                    return Integer.parseInt(key.replace("Week ", ""));
                }))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new  // preserve iteration order
                ));

// Add the sorted calendar to the Thymeleaf context.
        context.setVariable("courseCalendar", sortedCalendar);
        System.out.println(sortedCalendar);

// Add the course calendar to the Thymeleaf context

        List<BookSection> requiredReadingsList = bookSectionService.getRequiredReadingsForSection(section.getSectionId());
        String requiredReadingsHtml = requiredReadingsList.stream()
                .map(bs -> bs.getBook().getTitle() + " (ISBN: " + bs.getBook().getIsbn())
                .collect(Collectors.joining("<br/>"));

        List<BookSection> suggestedReadingsList = bookSectionService.getSuggestedReadingsForSection(section.getSectionId());
        String suggestedReadingsHtml = suggestedReadingsList.stream()
                .map(bs -> bs.getBook().getTitle() + " (ISBN: " + bs.getBook().getIsbn() + ")")
                .collect(Collectors.joining("<br/>"));

        context.setVariable("requiredReadings", requiredReadingsHtml);
        context.setVariable("suggestedReadings", suggestedReadingsHtml);
        System.out.println("Required Readings: " + requiredReadingsHtml);
        System.out.println("Suggested Readings: " + suggestedReadingsHtml);

        assessments = assessmentRepository.findAssessmentBySectionSectionId(section.getSectionId())
                .orElse(null);
        context.setVariable("assessments", assessments);


        // Process the Thymeleaf template (syllabus.html) into an HTML string
        String renderedHtml = templateEngine.process("syllabus", context);

        // Create or get the Syllabus entity, update its content with the rendered HTML, and save
        Syllabus syllabus = syllabusRepository.findSyllabusBySection(section)
                .orElse(new Syllabus());
        syllabus.setContent(renderedHtml);
        syllabus.setSection(section);

        return syllabusDTOMapper.apply(syllabusRepository.save(syllabus));
    }

    public void submitSyllabus(long syllabusId) {
        Syllabus syllabus = syllabusRepository.findById(syllabusId)
                .orElseThrow(() -> new EntityNotFoundException("Syllabus not found"));


        Section section = syllabus.getSection();
        List<Assessment> assessments = assessmentRepository.findAssessmentBySectionSectionId(section.getSectionId()).orElseThrow(() -> new IllegalStateException("No assessments created for this section"));

        int total = 0;
        for (Assessment a: assessments){
            total+=a.getMaxPoints();
        }

        if (total != 100){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You cannot submit the syllabus yet. Please ensure all assessments are created and their total weight equals 100.");
        }

        if (!syllabus.isSubmitted()){
            syllabus.setSubmitted(true);
            syllabus.setSubmissionDate(LocalDate.now());
        }

        syllabusRepository.save(syllabus);
    }

    public int getAssessmentTotal(long syllabusId) {
        Syllabus syllabus = syllabusRepository.findById(syllabusId)
                .orElseThrow(() -> new EntityNotFoundException("Syllabus not found"));

        Section section = syllabus.getSection();

        return assessmentRepository
                .findAssessmentBySectionSectionId(section.getSectionId())
                .map(list -> list.stream().mapToInt(Assessment::getMaxPoints).sum())
                .orElse(0);
    }

    public SyllabusDTO getSyllabusBySectionId(long sectionId) {
        Syllabus syllabus = syllabusRepository
                .findBySectionSectionId(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Syllabus not found"));

        return syllabusDTOMapper.apply(syllabus);
    }



}