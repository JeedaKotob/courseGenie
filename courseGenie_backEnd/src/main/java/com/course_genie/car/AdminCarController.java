package com.course_genie.car;

import com.course_genie.syllabus.ReminderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminCarController {

    private final AdminCarService adminCarService;
    private final ReminderService reminderService;

    public AdminCarController(AdminCarService adminCarService, ReminderService reminderService) {
        this.adminCarService = adminCarService;
        this.reminderService = reminderService;
    }

    @GetMapping("/car-progress")
    public Map<String, List<CarProgressDTO>> getCarProgress() {
        return adminCarService.getCarProgressByDepartment();
    }

    @PostMapping("/send-car-reminders")
    public ResponseEntity<String> sendCarReminders() {
        int count = reminderService.sendCarSubmissionReminders();
        return ResponseEntity.ok(count + " reminder emails sent successfully.");
    }
}
