package com.course_genie.semester;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SemesterService {
    private final SemesterRepository semesterRepository;

    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    public List<String> getAllSemesterNames() {
        return semesterRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Semester::getStartDate, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .map(Semester::getSemesterName)
                .filter(name -> name != null && !name.isBlank())
                .toList();
    }
}
