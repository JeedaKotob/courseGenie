package com.course_genie.examRoom;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRoomService {
    private final ExamRoomRepository examRoomRepository;
    private final ExamRoomDTOMapper examRoomDTOMapper;

    public ExamRoomService(ExamRoomRepository examRoomRepository, ExamRoomDTOMapper examRoomDTOMapper) {
        this.examRoomRepository = examRoomRepository;
        this.examRoomDTOMapper = examRoomDTOMapper;
    }

    public List<ExamRoomDTO> getAllRooms() {
        return examRoomRepository.findAll().stream().map(examRoomDTOMapper).toList();
    }

}
