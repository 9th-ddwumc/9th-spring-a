package com.example.umc9th_chapter4.domain.review.entity;

import com.example.umc9th_chapter4.domain.store.entity.Store;
import com.example.umc9th_chapter4.domain.user.entity.Users;
import com.example.umc9th_chapter4.global.jpa.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "review")
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Long id;

    // FK 보유 (연관관계 주인)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    // FK 보유 (연관관계 주인)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    // Text 컬럼
    @Lob
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    // 양방향: Review(1) : ReviewPhoto(N)
    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewPhoto> photos = new ArrayList<>();

    // 양방향(1:1) - FK는 reply 쪽이 가짐 → 여기서는 inverse
    @OneToOne(mappedBy = "review", fetch = FetchType.LAZY)
    private ReviewReply reply;
}
