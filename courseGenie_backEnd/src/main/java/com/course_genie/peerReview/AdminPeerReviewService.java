package com.course_genie.peerReview;

import com.course_genie.department.Department;
import com.course_genie.department.DepartmentRepository;
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

    public AdminPeerReviewService(
            UserRepository userRepository,
            DepartmentRepository departmentRepository,
            PeerReviewAssignmentRepository assignmentRepository
    ) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.assignmentRepository = assignmentRepository;
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
        int reviewsPerProfessor = Optional.ofNullable(request.reviewsPerProfessor()).orElse(1);
        if (reviewsPerProfessor <= 0) {
            throw new ResponseStatusException(BAD_REQUEST, "reviewsPerProfessor must be greater than 0.");
        }

        Department department = validateDepartment(departmentName);
        List<User> professors = userRepository.findProfessorsByDepartmentName(departmentName);
        if (professors.size() < 2) {
            throw new ResponseStatusException(BAD_REQUEST, "At least 2 professors are required for same-department pairing.");
        }

        int maxDirectionalPerProfessor = professors.size() - 1;
        if (reviewsPerProfessor > maxDirectionalPerProfessor) {
            throw new ResponseStatusException(
                    BAD_REQUEST,
                    "reviewsPerProfessor cannot exceed " + maxDirectionalPerProfessor + " for this department."
            );
        }

        List<User> ordered = new ArrayList<>(professors);
        ordered.sort(Comparator.comparing(User::getFullName, String.CASE_INSENSITIVE_ORDER));

        List<PeerReviewAssignment> generated = new ArrayList<>();
        for (int i = 0; i < ordered.size(); i++) {
            User reviewer = ordered.get(i);
            for (int offset = 1; offset <= reviewsPerProfessor; offset++) {
                User reviewee = ordered.get((i + offset) % ordered.size());
                generated.add(PeerReviewAssignment.builder()
                        .reviewer(reviewer)
                        .reviewee(reviewee)
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
            User reviewee = usersById.get(pair.revieweeId());
            if (reviewer == null || reviewee == null) {
                throw new ResponseStatusException(BAD_REQUEST, "Reviewer or reviewee is not a valid professor.");
            }
            validateSameDepartment(departmentName, reviewer, reviewee);
            if (Objects.equals(reviewer.getUserId(), reviewee.getUserId())) {
                throw new ResponseStatusException(BAD_REQUEST, "Self-review is not allowed.");
            }
            String key = reviewer.getUserId() + "->" + reviewee.getUserId();
            if (!seenPairs.add(key)) {
                throw new ResponseStatusException(BAD_REQUEST, "Duplicate assignment found for pair " + key + ".");
            }
            assignments.add(PeerReviewAssignment.builder()
                    .reviewer(reviewer)
                    .reviewee(reviewee)
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
        return new PeerReviewAssignmentDTO(
                assignment.getAssignmentId(),
                assignment.getReviewer().getUserId(),
                assignment.getReviewer().getFullName(),
                assignment.getReviewee().getUserId(),
                assignment.getReviewee().getFullName(),
                assignment.getDepartment().getDepartmentName(),
                assignment.getPairingSource()
        );
    }
}
