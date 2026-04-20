package com.course_genie.examSchedule;

import com.course_genie.examRoom.ExamRoom;
import com.course_genie.examRoom.ExamRoomRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExamScheduleService {
    private final ExamScheduleRepository examScheduleRepository;
    private final ExamRoomRepository examRoomRepository;

    public ExamScheduleService(
            ExamScheduleRepository examScheduleRepository,
            ExamRoomRepository examRoomRepository
    ) {
        this.examScheduleRepository = examScheduleRepository;
        this.examRoomRepository = examRoomRepository;
    }

    public List<ExamScheduleDTO> getByDate(LocalDate examDate) {
        return examScheduleRepository.findByExamDate(examDate)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional
    public List<ExamScheduleDTO> saveByDate(ExamScheduleSaveRequest request) {
        if (request == null || request.examDate() == null) {
            throw new IllegalArgumentException("Exam date is required.");
        }

        List<ExamSchedule> schedulesForDate = examScheduleRepository.findByExamDate(request.examDate());
        Map<Long, ExamSchedule> scheduleById = schedulesForDate.stream()
                .collect(Collectors.toMap(ExamSchedule::getExamScheduleId, schedule -> schedule));

        List<ExamScheduleAssignmentRequest> incomingAssignments =
                Optional.ofNullable(request.assignments()).orElse(List.of());

        for (ExamScheduleAssignmentRequest assignment : incomingAssignments) {
            if (!scheduleById.containsKey(assignment.examScheduleId())) {
                throw new EntityNotFoundException("Exam schedule not found: " + assignment.examScheduleId());
            }
        }

        validateRoomLoad(incomingAssignments, scheduleById);

        for (ExamScheduleAssignmentRequest assignment : incomingAssignments) {
            ExamSchedule schedule = scheduleById.get(assignment.examScheduleId());
            Set<Long> roomIds = new HashSet<>(Optional.ofNullable(assignment.roomIds()).orElse(List.of()));
            Set<ExamRoom> rooms = new HashSet<>(examRoomRepository.findAllById(roomIds));
            if (rooms.size() != roomIds.size()) {
                throw new EntityNotFoundException("One or more rooms do not exist.");
            }
            schedule.setAvailableRooms(rooms);
        }

        return examScheduleRepository.saveAll(schedulesForDate)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private void validateRoomLoad(
            List<ExamScheduleAssignmentRequest> assignments,
            Map<Long, ExamSchedule> scheduleById
    ) {
        Map<String, Integer> roomSlotUsage = new HashMap<>();

        for (ExamSchedule schedule : scheduleById.values()) {
            String slot = Optional.ofNullable(schedule.getTimeSlot()).orElse("").trim();
            Set<Long> roomIds = schedule.getAvailableRooms().stream().map(ExamRoom::getRoomId).collect(Collectors.toSet());
            for (Long roomId : roomIds) {
                String key = slot + "::" + roomId;
                roomSlotUsage.put(key, roomSlotUsage.getOrDefault(key, 0) + 1);
            }
        }

        for (ExamScheduleAssignmentRequest assignment : assignments) {
            ExamSchedule schedule = scheduleById.get(assignment.examScheduleId());
            String slot = Optional.ofNullable(schedule.getTimeSlot()).orElse("").trim();
            Set<Long> existingRoomIds = schedule.getAvailableRooms().stream().map(ExamRoom::getRoomId).collect(Collectors.toSet());
            for (Long roomId : existingRoomIds) {
                String key = slot + "::" + roomId;
                roomSlotUsage.put(key, roomSlotUsage.getOrDefault(key, 0) - 1);
            }

            Set<Long> newRoomIds = new HashSet<>(Optional.ofNullable(assignment.roomIds()).orElse(List.of()));
            for (Long roomId : newRoomIds) {
                String key = slot + "::" + roomId;
                int updatedCount = roomSlotUsage.getOrDefault(key, 0) + 1;
                if (updatedCount > 2) {
                    throw new IllegalArgumentException(
                            "Room " + roomId + " exceeds max of 2 exams in time slot " + schedule.getTimeSlot() + "."
                    );
                }
                roomSlotUsage.put(key, updatedCount);
            }
        }
    }

    private ExamScheduleDTO toDto(ExamSchedule examSchedule) {
        return ExamScheduleDTO.builder()
                .examScheduleId(examSchedule.getExamScheduleId())
                .examDate(examSchedule.getExamDate())
                .timeSlot(examSchedule.getTimeSlot())
                .semesterId(examSchedule.getSemester().getSemesterId())
                .semesterName(examSchedule.getSemester().getSemesterName())
                .courseId(examSchedule.getCourse().getCourseId())
                .courseCode(examSchedule.getCourse().getCode())
                .courseName(examSchedule.getCourse().getName())
                .roomIds(examSchedule.getAvailableRooms().stream().map(ExamRoom::getRoomId).collect(Collectors.toList()))
                .build();
    }
}
