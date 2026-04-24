package com.course_genie.peerReview;

import com.course_genie.section.Section;
import com.course_genie.section.SectionRepository;
import com.course_genie.user.User;
import com.course_genie.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ReviewerPeerReviewService {
    private final PeerReviewAssignmentRepository assignmentRepository;
    private final PeerReviewRepository peerReviewRepository;
    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    public ReviewerPeerReviewService(
            PeerReviewAssignmentRepository assignmentRepository,
            PeerReviewRepository peerReviewRepository,
            SectionRepository sectionRepository,
            UserRepository userRepository
    ) {
        this.assignmentRepository = assignmentRepository;
        this.peerReviewRepository = peerReviewRepository;
        this.sectionRepository = sectionRepository;
        this.userRepository = userRepository;
    }

    public List<ReviewerAssignmentDTO> getAssignments(Long reviewerId) {
        List<PeerReviewAssignment> assignments = assignmentRepository.findAll().stream()
                .filter(a -> a.getReviewer() != null && reviewerId.equals(a.getReviewer().getUserId()))
                .toList();

        return assignments.stream().map(assignment -> {
            Section section = resolveSection(assignment.getRevieweeSectionId());
            boolean completed = peerReviewRepository.findByAssignmentId(assignment.getAssignmentId()).isPresent();
            return new ReviewerAssignmentDTO(
                    assignment.getAssignmentId(),
                    assignment.getReviewer().getUserId(),
                    assignment.getReviewer().getFullName(),
                    assignment.getReviewee().getUserId(),
                    assignment.getReviewee().getFullName(),
                    assignment.getRevieweeSectionId(),
                    section != null && section.getCourse() != null ? section.getCourse().getCode() : "",
                    section != null && section.getCourse() != null ? section.getCourse().getName() : "",
                    section != null ? section.getCode() : "",
                    assignment.getDepartment() != null ? assignment.getDepartment().getDepartmentName() : "",
                    completed
            );
        }).toList();
    }

    @Transactional
    public void submitReview(ReviewerSubmitPeerReviewRequest request) {
        PeerReviewAssignment assignment = assignmentRepository.findById(request.assignmentId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Assignment not found."));
        if (!assignment.getReviewer().getUserId().equals(request.reviewerId())) {
            throw new ResponseStatusException(FORBIDDEN, "You are not allowed to submit this review.");
        }
        validateScore(request.alignmentScore(), "Alignment score");
        validateScore(request.assessmentDesignScore(), "Assessment design score");
        validateScore(request.gradingClarityScore(), "Grading clarity score");
        validateScore(request.feedbackEfficiencyScore(), "Feedback efficiency score");

        User reviewer = userRepository.findById(request.reviewerId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Reviewer not found."));

        Map<String, Object> criteriaPayload = new LinkedHashMap<>();
        criteriaPayload.put("alignmentScore", request.alignmentScore());
        criteriaPayload.put("alignmentComment", nullable(request.alignmentComment()));
        criteriaPayload.put("assessmentDesignScore", request.assessmentDesignScore());
        criteriaPayload.put("assessmentDesignComment", nullable(request.assessmentDesignComment()));
        criteriaPayload.put("gradingClarityScore", request.gradingClarityScore());
        criteriaPayload.put("gradingClarityComment", nullable(request.gradingClarityComment()));
        criteriaPayload.put("feedbackEfficiencyScore", request.feedbackEfficiencyScore());
        criteriaPayload.put("feedbackEfficiencyComment", nullable(request.feedbackEfficiencyComment()));

        Map<String, Object> appendixPayload = new LinkedHashMap<>();
        appendixPayload.put("courseGradeDistributionNote", nullable(request.courseGradeDistributionNote()));
        appendixPayload.put("courseReflectionNote", nullable(request.courseReflectionNote()));
        appendixPayload.put("innovationJourneyNote", nullable(request.innovationJourneyNote()));
        appendixPayload.put("otherNote", nullable(request.otherNote()));

        String criteriaJson = toJson(criteriaPayload);
        String appendixJson = toJson(appendixPayload);

        PeerReview existing = peerReviewRepository.findByAssignmentId(request.assignmentId()).orElse(null);
        PeerReview review = existing != null ? existing : new PeerReview();
        review.setReviewer(reviewer);
        review.setReviewee(assignment.getReviewee());
        review.setAssignmentId(assignment.getAssignmentId());
        review.setRevieweeSectionId(assignment.getRevieweeSectionId());
        review.setCriteria(criteriaJson);
        review.setAppendix(appendixJson);
        review.setSummary(nullable(request.summary()));
        review.setSubmittedAt(LocalDateTime.now());
        peerReviewRepository.save(review);
    }

    private void validateScore(Integer score, String fieldLabel) {
        if (score == null || score < 1 || score > 5) {
            throw new ResponseStatusException(BAD_REQUEST, fieldLabel + " must be between 1 and 5.");
        }
    }

    private String nullable(String value) {
        return value == null ? "" : value.trim();
    }

    private Section resolveSection(Long sectionId) {
        if (sectionId == null) return null;
        return sectionRepository.findById(sectionId).orElse(null);
    }

    private String toJson(Map<String, Object> values) {
        StringBuilder builder = new StringBuilder("{");
        boolean first = true;
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            if (!first) builder.append(",");
            first = false;
            builder.append("\"").append(entry.getKey()).append("\":");
            Object value = entry.getValue();
            if (value instanceof Number) {
                builder.append(value);
            } else {
                builder.append("\"").append(escapeJson(String.valueOf(value))).append("\"");
            }
        }
        builder.append("}");
        return builder.toString();
    }

    private String escapeJson(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
