package com.course_genie.peerReview;

import com.course_genie.professor.Professor;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeerReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long peerReviewId;

    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String criteria;

    @Column(length = 2000)
    private String summary;

    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String appendix;

    @ManyToOne
    @JoinColumn(name="reviewer_id",nullable=false)
    private Professor reviewer;

    @ManyToOne
    @JoinColumn(name="reviewee_id",nullable=false)
    private Professor reviewee;


}
