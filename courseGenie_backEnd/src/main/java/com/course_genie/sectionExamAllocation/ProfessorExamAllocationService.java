package com.course_genie.sectionExamAllocation;

import com.course_genie.enrollment.Enrollment;
import com.course_genie.enrollment.EnrollmentRepository;
import com.course_genie.examRoom.ExamRoom;
import com.course_genie.examRoom.ExamRoomRepository;
import com.course_genie.examSchedule.ExamSchedule;
import com.course_genie.examSchedule.ExamScheduleRepository;
import com.course_genie.section.Section;
import com.course_genie.section.SectionRepository;
import com.course_genie.user.User;
import com.course_genie.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProfessorExamAllocationService {
    private final SectionRepository sectionRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final ExamScheduleRepository examScheduleRepository;
    private final SectionExamAllocationRepository sectionExamAllocationRepository;
    private final ExamRoomRepository examRoomRepository;
    private final UserRepository userRepository;
    private final ExamAllocationEmailService examAllocationEmailService;

    public ProfessorExamAllocationService(
            SectionRepository sectionRepository,
            EnrollmentRepository enrollmentRepository,
            ExamScheduleRepository examScheduleRepository,
            SectionExamAllocationRepository sectionExamAllocationRepository,
            ExamRoomRepository examRoomRepository,
            UserRepository userRepository,
            ExamAllocationEmailService examAllocationEmailService
    ) {
        this.sectionRepository = sectionRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.examScheduleRepository = examScheduleRepository;
        this.sectionExamAllocationRepository = sectionExamAllocationRepository;
        this.examRoomRepository = examRoomRepository;
        this.userRepository = userRepository;
        this.examAllocationEmailService = examAllocationEmailService;
    }

    public ProfessorExamAllocationDTO getProfessorAllocationView(Long sectionId, Long professorId) {
        Section section = getOwnedSection(sectionId, professorId);
        ExamSchedule examSchedule = getExamScheduleForSection(section);
        List<Enrollment> enrolledStudents = getEnrolledStudents(sectionId);

        List<SectionExamAllocation> sectionAllocations = sectionExamAllocationRepository
                .findBySectionSectionIdAndExamScheduleExamScheduleId(sectionId, examSchedule.getExamScheduleId());

        Map<Long, Long> roomByEnrollmentId = sectionAllocations.stream()
                .collect(Collectors.toMap(
                        allocation -> allocation.getEnrollment().getEnrollmentId(),
                        allocation -> allocation.getExamRoom().getRoomId()
                ));

        List<ProfessorExamRoomDTO> roomDTOs = buildRoomDTOs(examSchedule, sectionAllocations);

        List<ProfessorExamStudentDTO> studentDTOs = enrolledStudents.stream()
                .sorted(Comparator.comparing((Enrollment e) -> e.getStudent().getFirstName())
                        .thenComparing(e -> e.getStudent().getLastName()))
                .map(enrollment -> new ProfessorExamStudentDTO(
                        enrollment.getEnrollmentId(),
                        enrollment.getStudent().getStudentId(),
                        enrollment.getStudent().getFirstName(),
                        enrollment.getStudent().getLastName(),
                        enrollment.getStudent().getEmail(),
                        roomByEnrollmentId.get(enrollment.getEnrollmentId())
                ))
                .toList();

        return new ProfessorExamAllocationDTO(
                examSchedule.getExamScheduleId(),
                examSchedule.getExamDate(),
                examSchedule.getStartTime(),
                examSchedule.getEndTime(),
                section.getSectionId(),
                section.getCode(),
                section.getCourse().getCourseId(),
                section.getCourse().getCode(),
                section.getCourse().getName(),
                section.getSemester().getSemesterId(),
                section.getSemester().getSemesterName(),
                enrolledStudents.size(),
                roomDTOs,
                studentDTOs
        );
    }

    @Transactional
    public ProfessorExamAllocationDTO saveProfessorAllocations(
            Long sectionId,
            Long professorId,
            SaveProfessorExamAllocationRequest request
    ) {
        if (request == null || request.examScheduleId() == null) {
            throw new IllegalArgumentException("Exam schedule is required.");
        }

        Section section = getOwnedSection(sectionId, professorId);
        ExamSchedule examSchedule = getExamScheduleForSection(section);
        if (!Objects.equals(examSchedule.getExamScheduleId(), request.examScheduleId())) {
            throw new IllegalArgumentException("Invalid exam schedule for this section.");
        }

        List<Enrollment> enrolledStudents = getEnrolledStudents(sectionId);
        Set<Long> enrolledIds = enrolledStudents.stream().map(Enrollment::getEnrollmentId).collect(Collectors.toSet());

        List<StudentRoomAssignmentRequest> assignments = Optional.ofNullable(request.assignments()).orElse(List.of());
        if (assignments.size() != enrolledIds.size()) {
            throw new IllegalArgumentException("All enrolled students must be assigned exactly once.");
        }

        Set<Long> assignmentEnrollmentIds = assignments.stream()
                .map(StudentRoomAssignmentRequest::enrollmentId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        if (!assignmentEnrollmentIds.equals(enrolledIds)) {
            throw new IllegalArgumentException("Assignments must include all enrolled students exactly once.");
        }

        Set<Long> allowedRoomIds = examSchedule.getAvailableRooms().stream()
                .map(ExamRoom::getRoomId)
                .collect(Collectors.toSet());
        if (allowedRoomIds.isEmpty()) {
            throw new IllegalArgumentException("No rooms available for this exam.");
        }

        for (StudentRoomAssignmentRequest assignment : assignments) {
            if (assignment.roomId() == null || !allowedRoomIds.contains(assignment.roomId())) {
                throw new IllegalArgumentException("Assignment contains a room not allowed by admin.");
            }
        }

        Map<Long, Long> globalRoomUsage = getSameSlotAllocations(examSchedule)
                .stream()
                .filter(allocation ->
                        !(Objects.equals(allocation.getSection().getSectionId(), sectionId)
                                && Objects.equals(allocation.getExamSchedule().getExamScheduleId(), examSchedule.getExamScheduleId()))
                )
                .collect(Collectors.groupingBy(
                        allocation -> allocation.getExamRoom().getRoomId(),
                        Collectors.counting()
                ));

        Map<Long, Long> incomingRoomUsage = assignments.stream()
                .collect(Collectors.groupingBy(StudentRoomAssignmentRequest::roomId, Collectors.counting()));

        Map<Long, ExamRoom> roomMap = examRoomRepository.findAllById(allowedRoomIds).stream()
                .collect(Collectors.toMap(ExamRoom::getRoomId, Function.identity()));

        for (Map.Entry<Long, Long> roomEntry : incomingRoomUsage.entrySet()) {
            Long roomId = roomEntry.getKey();
            long totalAssigned = globalRoomUsage.getOrDefault(roomId, 0L) + roomEntry.getValue();
            int capacity = roomMap.get(roomId).getCapacity();
            if (totalAssigned > capacity) {
                throw new IllegalArgumentException(
                        "Room " + roomMap.get(roomId).getRoomNumber() + " exceeds capacity."
                );
            }
        }

        Map<Long, Enrollment> enrollmentMap = enrolledStudents.stream()
                .collect(Collectors.toMap(Enrollment::getEnrollmentId, Function.identity()));

        sectionExamAllocationRepository.deleteBySectionSectionIdAndExamScheduleExamScheduleId(
                sectionId,
                examSchedule.getExamScheduleId()
        );

        List<SectionExamAllocation> toSave = assignments.stream()
                .map(assignment -> SectionExamAllocation.builder()
                        .examSchedule(examSchedule)
                        .section(section)
                        .enrollment(enrollmentMap.get(assignment.enrollmentId()))
                        .examRoom(roomMap.get(assignment.roomId()))
                        .build())
                .toList();

        sectionExamAllocationRepository.saveAll(toSave);

        return getProfessorAllocationView(sectionId, professorId);
    }

    public int notifyStudentsForSection(Long sectionId, Long professorId) {
        Section section = getOwnedSection(sectionId, professorId);
        ExamSchedule examSchedule = getExamScheduleForSection(section);

        List<SectionExamAllocation> allocations = sectionExamAllocationRepository
                .findBySectionSectionIdAndExamScheduleExamScheduleId(sectionId, examSchedule.getExamScheduleId());

        String examTime = buildSlotLabel(examSchedule);
        String examDate = String.valueOf(examSchedule.getExamDate());
        List<ExamAllocationEmailService.StudentExamNotificationPayload> notifications = new ArrayList<>();

        for (SectionExamAllocation allocation : allocations) {
            if (allocation.getEnrollment() == null
                    || allocation.getEnrollment().getStudent() == null
                    || allocation.getEnrollment().getStudent().getEmail() == null) {
                continue;
            }

            String studentName = allocation.getEnrollment().getStudent().getFirstName() + " "
                    + allocation.getEnrollment().getStudent().getLastName();
            notifications.add(new ExamAllocationEmailService.StudentExamNotificationPayload(
                    allocation.getEnrollment().getStudent().getEmail(),
                    studentName,
                    section.getCourse().getCode(),
                    section.getCourse().getName(),
                    section.getCode(),
                    examDate,
                    examTime,
                    allocation.getExamRoom().getRoomNumber()
            ));
        }

        if (!notifications.isEmpty()) {
            examAllocationEmailService.sendStudentExamRoomNotificationsAsync(notifications);
        }

        return notifications.size();
    }

    private Section getOwnedSection(Long sectionId, Long professorId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Section not found."));

        User professor = userRepository.findById(professorId)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found."));

        if (section.getProfessor() == null || !Objects.equals(section.getProfessor().getUserId(), professor.getUserId())) {
            throw new IllegalArgumentException("You are not allowed to manage this section.");
        }

        return section;
    }

    private ExamSchedule getExamScheduleForSection(Section section) {
        if (section.getSemester() == null) {
            throw new IllegalArgumentException("Section has no semester assigned.");
        }

        List<ExamSchedule> schedules = examScheduleRepository.findByCourseCourseIdAndSemesterSemesterIdOrderByExamDateDesc(
                section.getCourse().getCourseId(),
                section.getSemester().getSemesterId()
        );

        if (schedules.isEmpty()) {
            throw new EntityNotFoundException("No exam schedule configured by admin for this course and semester.");
        }

        return schedules.get(0);
    }

    private List<Enrollment> getEnrolledStudents(Long sectionId) {
        return enrollmentRepository.findEnrollmentBySectionSectionIdAndStatus(
                sectionId,
                Enrollment.EnrollmentStatus.ENROLLED
        );
    }

    private List<ProfessorExamRoomDTO> buildRoomDTOs(
            ExamSchedule examSchedule,
            List<SectionExamAllocation> sectionAllocations
    ) {
        Map<Long, Long> sectionRoomCount = sectionAllocations.stream()
                .collect(Collectors.groupingBy(
                        allocation -> allocation.getExamRoom().getRoomId(),
                        Collectors.counting()
                ));

        Map<Long, Long> slotRoomCount = getSameSlotAllocations(examSchedule)
                .stream()
                .collect(Collectors.groupingBy(
                        allocation -> allocation.getExamRoom().getRoomId(),
                        Collectors.counting()
                ));

        return examSchedule.getAvailableRooms().stream()
                .sorted(Comparator.comparing(ExamRoom::getRoomNumber))
                .map(room -> new ProfessorExamRoomDTO(
                        room.getRoomId(),
                        room.getRoomNumber(),
                        room.getRoomType().name(),
                        room.getCapacity(),
                        slotRoomCount.getOrDefault(room.getRoomId(), 0L),
                        sectionRoomCount.getOrDefault(room.getRoomId(), 0L)
                ))
                .toList();
    }

    private List<SectionExamAllocation> getSameSlotAllocations(ExamSchedule examSchedule) {
        if (examSchedule.getStartTime() == null || examSchedule.getEndTime() == null) {
            throw new IllegalArgumentException("Exam schedule must have start and end time.");
        }
        return sectionExamAllocationRepository.findByExamScheduleExamDateAndExamScheduleStartTimeAndExamScheduleEndTime(
                examSchedule.getExamDate(),
                examSchedule.getStartTime(),
                examSchedule.getEndTime()
        );
    }

    private String buildSlotLabel(ExamSchedule examSchedule) {
        if (examSchedule.getStartTime() == null || examSchedule.getEndTime() == null) {
            return "N/A";
        }
        return examSchedule.getStartTime().toString().substring(0, 5)
                + " - "
                + examSchedule.getEndTime().toString().substring(0, 5);
    }
}
