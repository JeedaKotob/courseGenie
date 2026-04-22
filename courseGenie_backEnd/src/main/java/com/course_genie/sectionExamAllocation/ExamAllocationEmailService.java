package com.course_genie.sectionExamAllocation;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ExamAllocationEmailService {
    private static final Logger logger = LoggerFactory.getLogger(ExamAllocationEmailService.class);
    private final JavaMailSender mailSender;

    public ExamAllocationEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendStudentExamRoomNotification(
            String studentEmail,
            String studentName,
            String courseCode,
            String courseName,
            String sectionCode,
            String examDate,
            String examTime,
            String roomNumber
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("CourseGenie+ <jeedakotob@gmail.com>");
        message.setTo(studentEmail);
        message.setSubject("[CourseGenie+] Exam Room Assignment");
        message.setText(
                "Dear " + studentName + ",\n\n" +
                        "You have been assigned an exam room.\n\n" +
                        "Course: " + courseCode + " - " + courseName + "\n" +
                        "Section: " + sectionCode + "\n" +
                        "Date: " + examDate + "\n" +
                        "Time: " + examTime + "\n" +
                        "Room: " + roomNumber + "\n\n" +
                        "Please arrive on time.\n\n" +
                        "Best regards,\n" +
                        "Course Genie+"
        );

        mailSender.send(message);
    }

    @Async
    public void sendStudentExamRoomNotificationsAsync(List<StudentExamNotificationPayload> notifications) {
        for (StudentExamNotificationPayload notification : notifications) {
            try {
                sendStudentExamRoomNotification(
                        notification.studentEmail(),
                        notification.studentName(),
                        notification.courseCode(),
                        notification.courseName(),
                        notification.sectionCode(),
                        notification.examDate(),
                        notification.examTime(),
                        notification.roomNumber()
                );
            } catch (Exception ex) {
                logger.error("Failed exam notification email for student {}", notification.studentEmail(), ex);
            }
        }
    }

    public record StudentExamNotificationPayload(
            String studentEmail,
            String studentName,
            String courseCode,
            String courseName,
            String sectionCode,
            String examDate,
            String examTime,
            String roomNumber
    ) {}
}
