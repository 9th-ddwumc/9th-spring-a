package com.example.umc9th_chapter4.domain.mission.entity;

import com.example.umc9th_chapter4.domain.store.entity.Store;
import com.example.umc9th_chapter4.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    // 양방향: Mission(1) : UserMission(N)
    @Builder.Default
    @OneToMany(mappedBy = "mission")
    private List<UserMission> userMissions = new ArrayList<>();
}
