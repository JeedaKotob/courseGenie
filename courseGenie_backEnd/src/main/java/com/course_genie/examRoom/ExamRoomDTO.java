package com.course_genie.examRoom;
import lombok.Builder;

@Builder
public record ExamRoomDTO (
        long roomId,
        int capacity,
        String roomNumber,
        ExamRoom.RoomType roomType
){}
