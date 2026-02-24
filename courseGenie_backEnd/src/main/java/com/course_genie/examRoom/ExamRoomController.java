package com.course_genie.examRoom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/examRooms")
public class ExamRoomController {
    private final ExamRoomService examRoomService;

    public ExamRoomController(ExamRoomService examRoomService) {
        this.examRoomService = examRoomService;
    }

    @GetMapping
    public List<ExamRoomDTO> getAllRooms(){
        return examRoomService.getAllRooms();
    }
}
