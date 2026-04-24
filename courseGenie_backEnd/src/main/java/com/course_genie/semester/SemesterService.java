package com.course_genie.semester;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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

    public Semester getCurrentSemesterOrThrow() {
        LocalDate today = LocalDate.now();
        List<Semester> all = semesterRepository.findAll();

        return all.stream()
                .filter(s -> s.getStartDate() != null && s.getEndDate() != null)
                .filter(s -> !today.isBefore(s.getStartDate()) && !today.isAfter(s.getEndDate()))
                .findFirst()
                .orElseGet(() -> all.stream()
                        .filter(s -> s.getStartDate() != null)
                        .max(Comparator.comparing(Semester::getStartDate))
                        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No semester configured.")));
    }
}
