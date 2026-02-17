package com.course_genie.syllabus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminSyllabusController {

    private final AdminSyllabusService adminSyllabusService;
    private final ReminderService reminderService;

    public AdminSyllabusController(AdminSyllabusService adminSyllabusService, ReminderService reminderService) {
        this.adminSyllabusService = adminSyllabusService;
        this.reminderService = reminderService;
    }

    @GetMapping("/syllabus-progress")
    public Map<String, List<SyllabusProgressDTO>> getSyllabusProgress() {
        return adminSyllabusService.getSyllabusProgressByDepartment();
    }

    @PostMapping("/send-reminders")
    public ResponseEntity<String> sendReminders() {
        int count = reminderService.sendSubmissionReminders();
        return ResponseEntity.ok(
                count + " reminder emails sent successfully."
        );
    }


}
