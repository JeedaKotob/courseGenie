package com.course_genie.examSchedule;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/examSchedules")
public class ExamScheduleController {
    private final ExamScheduleService examScheduleService;

    public ExamScheduleController(ExamScheduleService examScheduleService) {
        this.examScheduleService = examScheduleService;
    }

    @GetMapping
    public List<ExamScheduleDTO> getByDate(@RequestParam LocalDate examDate) {
        return examScheduleService.getByDate(examDate);
    }

    @PutMapping
    public ResponseEntity<List<ExamScheduleDTO>> saveByDate(@RequestBody ExamScheduleSaveRequest request) {
        return ResponseEntity.ok(examScheduleService.saveByDate(request));
    }
}
