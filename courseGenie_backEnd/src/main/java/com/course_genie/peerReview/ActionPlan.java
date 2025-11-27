package com.course_genie.peerReview;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actionPlanId;

    @Column(length = 2000)
    private String plan;

    @OneToOne
    @JoinColumn(name = "peer_review_id", nullable = false)
    private PeerReview peerReview;
}
