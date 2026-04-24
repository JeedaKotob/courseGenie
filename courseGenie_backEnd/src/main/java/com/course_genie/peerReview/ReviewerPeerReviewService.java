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
    private final ActionPlanRepository actionPlanRepository;
    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    public ReviewerPeerReviewService(
            PeerReviewAssignmentRepository assignmentRepository,
            PeerReviewRepository peerReviewRepository,
            ActionPlanRepository actionPlanRepository,
            SectionRepository sectionRepository,
            UserRepository userRepository
    ) {
        this.assignmentRepository = assignmentRepository;
        this.peerReviewRepository = peerReviewRepository;
        this.actionPlanRepository = actionPlanRepository;
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

    public List<RevieweeReceivedReviewDTO> getReceivedReviews(Long revieweeId) {
        List<PeerReview> reviews = peerReviewRepository.findByRevieweeUserId(revieweeId);
        return reviews.stream().map(review -> {
            Section section = resolveSection(review.getRevieweeSectionId());
            ActionPlan actionPlanEntity = actionPlanRepository.findByPeerReviewPeerReviewId(review.getPeerReviewId())
                    .orElse(null);
            String actionPlan = actionPlanEntity != null ? actionPlanEntity.getPlan() : "";
            String criteria = nullable(review.getCriteria());
            String appendix = nullable(review.getAppendix());
            return new RevieweeReceivedReviewDTO(
                    review.getPeerReviewId(),
                    review.getReviewee().getUserId(),
                    review.getReviewer().getFullName(),
                    section != null && section.getCourse() != null ? section.getCourse().getCode() : "",
                    section != null && section.getCourse() != null ? section.getCourse().getName() : "",
                    section != null ? section.getCode() : "",
                    parseIntegerField(criteria, "alignmentScore"),
                    parseStringField(criteria, "alignmentComment"),
                    parseIntegerField(criteria, "assessmentDesignScore"),
                    parseStringField(criteria, "assessmentDesignComment"),
                    parseIntegerField(criteria, "gradingClarityScore"),
                    parseStringField(criteria, "gradingClarityComment"),
                    parseIntegerField(criteria, "feedbackEfficiencyScore"),
                    parseStringField(criteria, "feedbackEfficiencyComment"),
                    parseStringField(appendix, "courseGradeDistributionNote"),
                    parseStringField(appendix, "courseReflectionNote"),
                    parseStringField(appendix, "innovationJourneyNote"),
                    parseStringField(appendix, "otherNote"),
                    nullable(review.getSummary()),
                    review.getSubmittedAt(),
                    nullable(actionPlan),
                    actionPlanEntity != null && actionPlanEntity.isSubmitted(),
                    actionPlanEntity != null ? actionPlanEntity.getSubmittedAt() : null
            );
        }).toList();
    }

    @Transactional
    public void saveReflection(RevieweeReflectionRequest request) {
        PeerReview review = peerReviewRepository.findById(request.peerReviewId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Peer review not found."));
        if (!review.getReviewee().getUserId().equals(request.revieweeId())) {
            throw new ResponseStatusException(FORBIDDEN, "You are not allowed to submit reflection for this review.");
        }
        if (nullable(request.actionPlan()).isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "Reflection cannot be empty.");
        }
        ActionPlan plan = actionPlanRepository.findByPeerReviewPeerReviewId(review.getPeerReviewId())
                .orElse(ActionPlan.builder().peerReview(review).build());
        if (plan.isSubmitted()) {
            throw new ResponseStatusException(BAD_REQUEST, "Reflection already submitted and cannot be changed.");
        }
        plan.setPlan(nullable(request.actionPlan()));
        plan.setSubmitted(true);
        plan.setSubmittedAt(LocalDateTime.now());
        actionPlanRepository.save(plan);
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

    private String parseStringField(String json, String key) {
        String marker = "\"" + key + "\":\"";
        int start = json.indexOf(marker);
        if (start < 0) return "";
        start += marker.length();
        int end = json.indexOf("\"", start);
        if (end < 0) return "";
        return json.substring(start, end)
                .replace("\\\"", "\"")
                .replace("\\\\", "\\");
    }

    private Integer parseIntegerField(String json, String key) {
        String marker = "\"" + key + "\":";
        int start = json.indexOf(marker);
        if (start < 0) return null;
        start += marker.length();
        int end = start;
        while (end < json.length() && Character.isDigit(json.charAt(end))) {
            end++;
        }
        if (end == start) return null;
        try {
            return Integer.parseInt(json.substring(start, end));
        } catch (NumberFormatException ignored) {
            return null;
        }
    }
}
