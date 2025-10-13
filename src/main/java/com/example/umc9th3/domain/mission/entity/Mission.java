package com.example.umc9th3.domain.mission.entity;

import com.example.umc9th3.domain.store.entity.Store;
import com.example.umc9th3.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "mission")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "reward_point", nullable = false)
    private Integer rewardPoint;
}