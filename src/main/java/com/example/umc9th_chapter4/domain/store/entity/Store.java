package com.example.umc9th_chapter4.domain.store.entity;

import com.example.umc9th_chapter4.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", nullable = false)
    private Long id;

    // FK 보유 (연관관계 주인)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "manager_code", nullable = false)
    private Long managerCode;

    @Column(name = "detail_address", nullable = false, length = 100)
    private String detailAddress;

    // 양방향: Store(1) : Review(N)
    @Builder.Default
    @OneToMany(mappedBy = "store")
    private List<Review> reviews = new ArrayList<>();
}
