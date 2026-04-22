package com.course_genie.examSchedule;

import com.course_genie.enrollment.Enrollment;
import com.course_genie.enrollment.EnrollmentRepository;
import com.course_genie.examRoom.ExamRoom;
import com.course_genie.examRoom.ExamRoomRepository;
import com.course_genie.sectionExamAllocation.SectionExamAllocation;
import com.course_genie.sectionExamAllocation.SectionExamAllocationRepository;
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
    private final EnrollmentRepository enrollmentRepository;
    private final SectionExamAllocationRepository sectionExamAllocationRepository;

    public ExamScheduleService(
            ExamScheduleRepository examScheduleRepository,
            ExamRoomRepository examRoomRepository,
            EnrollmentRepository enrollmentRepository,
            SectionExamAllocationRepository sectionExamAllocationRepository
    ) {
        this.examScheduleRepository = examScheduleRepository;
        this.examRoomRepository = examRoomRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.sectionExamAllocationRepository = sectionExamAllocationRepository;
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
            String slot = buildSlotKey(schedule);
            Set<Long> roomIds = schedule.getAvailableRooms().stream().map(ExamRoom::getRoomId).collect(Collectors.toSet());
            for (Long roomId : roomIds) {
                String key = slot + "::" + roomId;
                roomSlotUsage.put(key, roomSlotUsage.getOrDefault(key, 0) + 1);
            }
        }

        for (ExamScheduleAssignmentRequest assignment : assignments) {
            ExamSchedule schedule = scheduleById.get(assignment.examScheduleId());
            String slot = buildSlotKey(schedule);
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
                            "Room " + roomId + " exceeds max of 2 exams between "
                                    + schedule.getStartTime() + " and " + schedule.getEndTime() + "."
                    );
                }
                roomSlotUsage.put(key, updatedCount);
            }
        }
    }

    private ExamScheduleDTO toDto(ExamSchedule examSchedule) {
        long enrolledStudentCount = enrollmentRepository.countBySectionCourseCourseIdAndSectionSemesterSemesterIdAndStatus(
                examSchedule.getCourse().getCourseId(),
                examSchedule.getSemester().getSemesterId(),
                Enrollment.EnrollmentStatus.ENROLLED
        );

        int assignedSeatCapacity = examSchedule.getAvailableRooms()
                .stream()
                .mapToInt(room -> Math.max(0, room.getCapacity() - getAllocatedInSameSlot(examSchedule, room.getRoomId())))
                .sum();

        List<RoomSeatAvailabilityDTO> roomSeatAvailability = examSchedule.getAvailableRooms()
                .stream()
                .map(room -> RoomSeatAvailabilityDTO.builder()
                        .roomId(room.getRoomId())
                        .remainingSeats(Math.max(0, room.getCapacity() - getAllocatedInSameSlot(examSchedule, room.getRoomId())))
                        .build())
                .toList();

        return ExamScheduleDTO.builder()
                .examScheduleId(examSchedule.getExamScheduleId())
                .examDate(examSchedule.getExamDate())
                .startTime(examSchedule.getStartTime())
                .endTime(examSchedule.getEndTime())
                .semesterId(examSchedule.getSemester().getSemesterId())
                .semesterName(examSchedule.getSemester().getSemesterName())
                .courseId(examSchedule.getCourse().getCourseId())
                .courseCode(examSchedule.getCourse().getCode())
                .courseName(examSchedule.getCourse().getName())
                .roomIds(examSchedule.getAvailableRooms().stream().map(ExamRoom::getRoomId).collect(Collectors.toList()))
                .roomSeatAvailability(roomSeatAvailability)
                .enrolledStudentCount(enrolledStudentCount)
                .assignedSeatCapacity(assignedSeatCapacity)
                .build();
    }

    private int getAllocatedInSameSlot(ExamSchedule examSchedule, Long roomId) {
        List<SectionExamAllocation> sameSlotAllocations = sectionExamAllocationRepository
                .findByExamScheduleExamDateAndExamScheduleStartTimeAndExamScheduleEndTime(
                        examSchedule.getExamDate(),
                        examSchedule.getStartTime(),
                        examSchedule.getEndTime()
                );

        long count = sameSlotAllocations.stream()
                .filter(allocation -> Objects.equals(allocation.getExamRoom().getRoomId(), roomId))
                .count();

        return (int) count;
    }

    private String buildSlotKey(ExamSchedule examSchedule) {
        if (examSchedule.getStartTime() == null || examSchedule.getEndTime() == null) {
            throw new IllegalArgumentException("Exam schedule must have start and end time.");
        }
        return examSchedule.getStartTime() + "_" + examSchedule.getEndTime();
    }
}
