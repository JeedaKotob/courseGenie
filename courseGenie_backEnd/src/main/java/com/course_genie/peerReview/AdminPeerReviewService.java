package com.course_genie.peerReview;

import com.course_genie.department.Department;
import com.course_genie.department.DepartmentRepository;
import com.course_genie.section.Section;
import com.course_genie.section.SectionRepository;
import com.course_genie.user.User;
import com.course_genie.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AdminPeerReviewService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final PeerReviewAssignmentRepository assignmentRepository;
    private final SectionRepository sectionRepository;

    public AdminPeerReviewService(
            UserRepository userRepository,
            DepartmentRepository departmentRepository,
            PeerReviewAssignmentRepository assignmentRepository,
            SectionRepository sectionRepository
    ) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.assignmentRepository = assignmentRepository;
        this.sectionRepository = sectionRepository;
    }

    public List<PeerReviewDepartmentOverviewDTO> getDepartmentOverviews() {
        List<User> professors = userRepository.findByRoles("ROLE_PROFESSOR");
        Map<String, List<User>> grouped = professors.stream()
                .filter(user -> user.getDepartment() != null && user.getDepartment().getDepartmentName() != null)
                .collect(Collectors.groupingBy(user -> user.getDepartment().getDepartmentName().trim()));

        return grouped.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(String.CASE_INSENSITIVE_ORDER))
                .map(entry -> new PeerReviewDepartmentOverviewDTO(
                        entry.getKey(),
                        entry.getValue().stream()
                                .map(user -> new ProfessorOptionDTO(user.getUserId(), user.getFullName(), user.getEmail()))
                                .sorted(Comparator.comparing(ProfessorOptionDTO::fullName, String.CASE_INSENSITIVE_ORDER))
                                .toList(),
                        getDepartmentSections(entry.getKey()).stream()
                                .map(section -> new RevieweeSectionOptionDTO(
                                        section.getSectionId(),
                                        section.getCourse() != null ? section.getCourse().getCode() : "",
                                        section.getCourse() != null ? section.getCourse().getName() : "",
                                        section.getCode(),
                                        section.getProfessor().getUserId(),
                                        section.getProfessor().getFullName()
                                ))
                                .sorted(Comparator
                                        .comparing(RevieweeSectionOptionDTO::courseCode, String.CASE_INSENSITIVE_ORDER)
                                        .thenComparing(RevieweeSectionOptionDTO::sectionCode, String.CASE_INSENSITIVE_ORDER))
                                .toList(),
                        assignmentRepository.findByDepartmentDepartmentNameIgnoreCase(entry.getKey()).size()
                ))
                .toList();
    }

    public List<PeerReviewAssignmentDTO> getAssignmentsByDepartment(String departmentName) {
        validateDepartment(departmentName);
        return assignmentRepository.findByDepartmentDepartmentNameIgnoreCase(departmentName).stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional
    public List<PeerReviewAssignmentDTO> autoPair(PeerReviewAutoPairRequest request) {
        String departmentName = request.departmentName();
        int reviewsPerSection = Optional.ofNullable(request.reviewsPerSection()).orElse(1);
        if (reviewsPerSection <= 0) {
            throw new ResponseStatusException(BAD_REQUEST, "reviewsPerSection must be greater than 0.");
        }

        Department department = validateDepartment(departmentName);
        List<User> professors = userRepository.findProfessorsByDepartmentName(departmentName);
        List<Section> sections = new ArrayList<>(getDepartmentSections(departmentName));
        if (professors.size() < 2 || sections.isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "At least 2 professors and 1 section are required.");
        }

        int maxReviewersPerSection = professors.size() - 1;
        if (reviewsPerSection > maxReviewersPerSection) {
            throw new ResponseStatusException(
                    BAD_REQUEST,
                    "reviewsPerSection cannot exceed " + maxReviewersPerSection + " for this department."
            );
        }

        List<User> reviewers = new ArrayList<>(professors);
        reviewers.sort(Comparator.comparing(User::getFullName, String.CASE_INSENSITIVE_ORDER));
        sections.sort(Comparator
                .comparing((Section s) -> s.getCourse() != null ? s.getCourse().getCode() : "", String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Section::getCode, String.CASE_INSENSITIVE_ORDER));
        Map<Long, Integer> reviewerLoad = reviewers.stream()
                .collect(Collectors.toMap(User::getUserId, r -> 0));

        List<PeerReviewAssignment> generated = new ArrayList<>();
        for (Section section : sections) {
            for (int slot = 0; slot < reviewsPerSection; slot++) {
                User reviewee = section.getProfessor();
                User reviewer = reviewers.stream()
                        .filter(candidate -> !Objects.equals(candidate.getUserId(), reviewee.getUserId()))
                        .sorted(Comparator.comparingInt(candidate -> reviewerLoad.getOrDefault(candidate.getUserId(), 0)))
                        .findFirst()
                        .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "No eligible reviewer found for section " + section.getCode()));
                reviewerLoad.put(reviewer.getUserId(), reviewerLoad.getOrDefault(reviewer.getUserId(), 0) + 1);
                generated.add(PeerReviewAssignment.builder()
                        .reviewer(reviewer)
                        .reviewee(reviewee)
                        .revieweeSectionId(section.getSectionId())
                        .department(department)
                        .pairingSource("AUTO")
                        .build());
            }
        }

        assignmentRepository.deleteByDepartmentDepartmentNameIgnoreCase(departmentName);
        return assignmentRepository.saveAll(generated).stream().map(this::toDTO).toList();
    }

    @Transactional
    public List<PeerReviewAssignmentDTO> saveManualAssignments(PeerReviewManualAssignmentRequest request) {
        String departmentName = request.departmentName();
        Department department = validateDepartment(departmentName);

        List<PeerReviewPairRequest> pairs = Optional.ofNullable(request.assignments()).orElse(List.of());
        if (pairs.isEmpty()) {
            assignmentRepository.deleteByDepartmentDepartmentNameIgnoreCase(departmentName);
            return List.of();
        }

        Map<Long, User> usersById = userRepository.findByRoles("ROLE_PROFESSOR").stream()
                .collect(Collectors.toMap(User::getUserId, u -> u));

        Set<String> seenPairs = new HashSet<>();
        List<PeerReviewAssignment> assignments = new ArrayList<>();

        for (PeerReviewPairRequest pair : pairs) {
            User reviewer = usersById.get(pair.reviewerId());
            Section revieweeSection = sectionRepository.findById(pair.revieweeSectionId())
                    .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Selected reviewee section is invalid."));
            User reviewee = revieweeSection.getProfessor();
            if (reviewer == null || reviewee == null) {
                throw new ResponseStatusException(BAD_REQUEST, "Reviewer or section owner is not a valid professor.");
            }
            validateSameDepartment(departmentName, reviewer, reviewee);
            if (Objects.equals(reviewer.getUserId(), reviewee.getUserId())) {
                throw new ResponseStatusException(BAD_REQUEST, "Reviewer cannot review their own section.");
            }
            String key = reviewer.getUserId() + "->" + revieweeSection.getSectionId();
            if (!seenPairs.add(key)) {
                throw new ResponseStatusException(BAD_REQUEST, "Duplicate assignment found for pair " + key + ".");
            }
            assignments.add(PeerReviewAssignment.builder()
                    .reviewer(reviewer)
                    .reviewee(reviewee)
                    .revieweeSectionId(revieweeSection.getSectionId())
                    .department(department)
                    .pairingSource("MANUAL")
                    .build());
        }

        assignmentRepository.deleteByDepartmentDepartmentNameIgnoreCase(departmentName);
        return assignmentRepository.saveAll(assignments).stream().map(this::toDTO).toList();
    }

    private Department validateDepartment(String departmentName) {
        if (departmentName == null || departmentName.trim().isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "departmentName is required.");
        }
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName.trim())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Department not found."));
    }

    private void validateSameDepartment(String departmentName, User reviewer, User reviewee) {
        String reviewerDept = reviewer.getDepartment() != null ? reviewer.getDepartment().getDepartmentName() : "";
        String revieweeDept = reviewee.getDepartment() != null ? reviewee.getDepartment().getDepartmentName() : "";
        if (!departmentName.equalsIgnoreCase(reviewerDept) || !departmentName.equalsIgnoreCase(revieweeDept)) {
            throw new ResponseStatusException(BAD_REQUEST, "Reviewer and reviewee must be in the selected department.");
        }
    }

    private PeerReviewAssignmentDTO toDTO(PeerReviewAssignment assignment) {
        Section section = resolveSection(assignment.getRevieweeSectionId());
        return new PeerReviewAssignmentDTO(
                assignment.getAssignmentId(),
                assignment.getReviewer().getUserId(),
                assignment.getReviewer().getFullName(),
                assignment.getReviewee().getUserId(),
                assignment.getReviewee().getFullName(),
                assignment.getRevieweeSectionId(),
                section != null && section.getCourse() != null ? section.getCourse().getCode() : "",
                section != null && section.getCourse() != null ? section.getCourse().getName() : "",
                section != null ? section.getCode() : "",
                assignment.getDepartment().getDepartmentName(),
                assignment.getPairingSource()
        );
    }

    private List<Section> getDepartmentSections(String departmentName) {
        return sectionRepository.findAll().stream()
                .filter(section -> section.getProfessor() != null)
                .filter(section -> section.getProfessor().getDepartment() != null)
                .filter(section -> {
                    String profDepartment = section.getProfessor().getDepartment().getDepartmentName();
                    return profDepartment != null && profDepartment.equalsIgnoreCase(departmentName);
                })
                .toList();
    }

    private Section resolveSection(Long sectionId) {
        if (sectionId == null) {
            return null;
        }
        return sectionRepository.findById(sectionId).orElse(null);
    }
}
