package com.course_genie.syllabus;

import com.course_genie.car.Car;
import com.course_genie.car.CarRepository;
import com.course_genie.section.Section;
import com.course_genie.section.SectionRepository;
import com.course_genie.user.User;
import com.course_genie.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final SectionRepository sectionRepository;
    private final CarRepository carRepository;

    public ReminderService(UserRepository userRepository,
                           EmailService emailService,
                           SectionRepository sectionRepository,
                           CarRepository carRepository) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.sectionRepository = sectionRepository;
        this.carRepository = carRepository;
    }

    public int sendSubmissionReminders() {

        List<User> professors =
                userRepository.findProfessorsWithUnsubmittedSyllabi();

        for (User prof : professors) {
            emailService.sendReminder(
                    prof.getEmail(),
                    prof.getFullName()
            );
        }

        return professors.size();
    }

    public int sendCarSubmissionReminders() {
        List<User> professors = userRepository.findByRoles("ROLE_PROFESSOR");

        List<User> professorsWithMissingCars = professors.stream()
                .filter(this::hasUnsubmittedCars)
                .toList();

        for (User prof : professorsWithMissingCars) {
            emailService.sendCarReminder(
                    prof.getEmail(),
                    prof.getFullName()
            );
        }

        return professorsWithMissingCars.size();
    }

    private boolean hasUnsubmittedCars(User professor) {
        List<Section> sections = sectionRepository.findByProfessorUserId(professor.getUserId());
        return sections.stream().anyMatch(section -> {
            Car car = carRepository.findCarBySectionSectionId(section.getSectionId()).orElse(null);
            return car == null || !car.isSubmitted();
        });
    }
}

