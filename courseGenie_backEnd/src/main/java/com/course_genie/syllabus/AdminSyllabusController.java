package com.course_genie.syllabus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminSyllabusController {

    private final AdminSyllabusService adminSyllabusService;

    public AdminSyllabusController(AdminSyllabusService adminSyllabusService) {
        this.adminSyllabusService = adminSyllabusService;
    }

    @GetMapping("/syllabus-progress")
    public List<SyllabusProgressDTO> getSyllabusProgress() {
        return adminSyllabusService.getSyllabusProgressByProfessor();
    }
}
