package com.course_genie.sectionExamAllocation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SectionExamAllocationRepository extends JpaRepository<SectionExamAllocation, Long> {
    List<SectionExamAllocation> findBySectionSectionIdAndExamScheduleExamScheduleId(Long sectionId, Long examScheduleId);

    List<SectionExamAllocation> findByExamScheduleExamScheduleId(Long examScheduleId);

    List<SectionExamAllocation> findByExamScheduleExamDateAndExamScheduleStartTimeAndExamScheduleEndTime(
            LocalDate examDate,
            LocalTime startTime,
            LocalTime endTime
    );

    void deleteBySectionSectionIdAndExamScheduleExamScheduleId(Long sectionId, Long examScheduleId);
    void deleteBySectionSectionId(Long sectionId);

    long countByExamScheduleExamScheduleIdAndExamRoomRoomId(Long examScheduleId, Long roomId);
}
