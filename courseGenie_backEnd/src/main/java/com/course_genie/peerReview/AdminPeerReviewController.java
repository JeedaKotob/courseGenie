package com.course_genie.peerReview;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/peer-review")
public class AdminPeerReviewController {
    private final AdminPeerReviewService adminPeerReviewService;

    public AdminPeerReviewController(AdminPeerReviewService adminPeerReviewService) {
        this.adminPeerReviewService = adminPeerReviewService;
    }

    @GetMapping("/departments")
    public List<PeerReviewDepartmentOverviewDTO> getDepartments() {
        return adminPeerReviewService.getDepartmentOverviews();
    }

    @GetMapping("/assignments")
    public List<PeerReviewAssignmentDTO> getAssignments(@RequestParam String departmentName) {
        return adminPeerReviewService.getAssignmentsByDepartment(departmentName);
    }

    @PostMapping("/auto-pair")
    public List<PeerReviewAssignmentDTO> autoPair(@RequestBody PeerReviewAutoPairRequest request) {
        return adminPeerReviewService.autoPair(request);
    }

    @PostMapping("/assignments")
    public List<PeerReviewAssignmentDTO> saveAssignments(@RequestBody PeerReviewManualAssignmentRequest request) {
        return adminPeerReviewService.saveManualAssignments(request);
    }
}
