package com.course_genie.peerReview;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peer-review/reviewer")
public class ReviewerPeerReviewController {
    private final ReviewerPeerReviewService reviewerPeerReviewService;

    public ReviewerPeerReviewController(ReviewerPeerReviewService reviewerPeerReviewService) {
        this.reviewerPeerReviewService = reviewerPeerReviewService;
    }

    @GetMapping("/assignments")
    public List<ReviewerAssignmentDTO> getAssignments(@RequestParam Long reviewerId) {
        return reviewerPeerReviewService.getAssignments(reviewerId);
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitReview(@RequestBody ReviewerSubmitPeerReviewRequest request) {
        reviewerPeerReviewService.submitReview(request);
        return ResponseEntity.ok("Peer review submitted successfully.");
    }
}
