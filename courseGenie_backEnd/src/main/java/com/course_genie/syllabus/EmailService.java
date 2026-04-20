package com.course_genie.syllabus;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;


@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendReminder(String to, String professorName) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("CourseGenie+ <jeedakotob@gmail.com>");
        message.setTo(to);
        message.setSubject("[CourseGenie+] Syllabus Submission Reminder");
        message.setText(
                "Dear Dr. " + professorName + ",\n\n" +
                        "This is an automated reminder from Course Genie.\n\n" +
                        "Our records show that one or more syllabi have not yet been submitted.\n\n" +
                        "Please log in to the system to complete submission.\n\n" +
                        "Thank you,\n" +
                        "Course Genie+"
        );


        mailSender.send(message);
    }
}
