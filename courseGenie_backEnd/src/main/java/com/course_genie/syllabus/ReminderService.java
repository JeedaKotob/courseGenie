package com.course_genie.syllabus;

import com.course_genie.user.User;
import com.course_genie.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public ReminderService(UserRepository userRepository,
                           EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
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
}

