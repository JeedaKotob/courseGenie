package com.course_genie.syllabus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminSyllabusController {

    private final AdminSyllabusService adminSyllabusService;

    public AdminSyllabusController(AdminSyllabusService adminSyllabusService) {
        this.adminSyllabusService = adminSyllabusService;
    }

    @GetMapping("/syllabus-progress")
    public Map<String, List<SyllabusProgressDTO>> getSyllabusProgress() {
        return adminSyllabusService.getSyllabusProgressByDepartment();
    }
}
