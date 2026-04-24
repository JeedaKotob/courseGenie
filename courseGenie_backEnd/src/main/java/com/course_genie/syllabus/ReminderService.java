package com.course_genie.syllabus;

import com.course_genie.car.Car;
import com.course_genie.car.CarRepository;
import com.course_genie.section.Section;
import com.course_genie.section.SectionRepository;
import com.course_genie.semester.Semester;
import com.course_genie.semester.SemesterService;
import com.course_genie.user.User;
import com.course_genie.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ReminderService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final SectionRepository sectionRepository;
    private final CarRepository carRepository;
    private final SyllabusRepository syllabusRepository;
    private final SemesterService semesterService;

    public ReminderService(UserRepository userRepository,
                           EmailService emailService,
                           SectionRepository sectionRepository,
                           CarRepository carRepository,
                           SyllabusRepository syllabusRepository,
                           SemesterService semesterService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.sectionRepository = sectionRepository;
        this.carRepository = carRepository;
        this.syllabusRepository = syllabusRepository;
        this.semesterService = semesterService;
    }

    public int sendSubmissionReminders() {
        Semester currentSemester = semesterService.getCurrentSemesterOrThrow();
        List<User> professors = userRepository.findByRoles("ROLE_PROFESSOR").stream()
                .filter(professor -> hasUnsubmittedSyllabi(professor, currentSemester.getSemesterId()))
                .toList();

        for (User prof : professors) {
            emailService.sendReminder(
                    prof.getEmail(),
                    prof.getFullName()
            );
        }

        return professors.size();
    }

    public int sendCarSubmissionReminders() {
        Semester currentSemester = semesterService.getCurrentSemesterOrThrow();
        List<User> professors = userRepository.findByRoles("ROLE_PROFESSOR");

        List<User> professorsWithMissingCars = professors.stream()
                .filter(prof -> hasUnsubmittedCars(prof, currentSemester.getSemesterId()))
                .toList();

        for (User prof : professorsWithMissingCars) {
            emailService.sendCarReminder(
                    prof.getEmail(),
                    prof.getFullName()
            );
        }

        return professorsWithMissingCars.size();
    }

    private boolean hasUnsubmittedCars(User professor, Long semesterId) {
        List<Section> sections = sectionRepository.findByProfessorUserId(professor.getUserId()).stream()
                .filter(section -> section.getSemester() != null)
                .filter(section -> Objects.equals(section.getSemester().getSemesterId(), semesterId))
                .toList();
        return sections.stream().anyMatch(section -> {
            Car car = carRepository.findCarBySectionSectionId(section.getSectionId()).orElse(null);
            return car == null || !car.isSubmitted();
        });
    }

    private boolean hasUnsubmittedSyllabi(User professor, Long semesterId) {
        List<Section> sections = sectionRepository.findByProfessorUserId(professor.getUserId()).stream()
                .filter(section -> section.getSemester() != null)
                .filter(section -> Objects.equals(section.getSemester().getSemesterId(), semesterId))
                .toList();
        return sections.stream().anyMatch(section ->
                syllabusRepository.findSyllabusBySection(section)
                        .map(syllabus -> !syllabus.isSubmitted())
                        .orElse(true)
        );
    }
}

