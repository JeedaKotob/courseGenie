package com.course_genie.examRoom;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class ExamRoomDTOMapper implements Function<ExamRoom, ExamRoomDTO> {
    @Override
    public ExamRoomDTO apply(ExamRoom examRoom) {
        return ExamRoomDTO.builder()
                .roomId(examRoom.getRoomId())
                .capacity(examRoom.getCapacity())
                .roomNumber(examRoom.getRoomNumber())
                .roomType(examRoom.getRoomType())
                .build();

    }
}
