package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.restraunt.entity.Restaurant;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissions = new ArrayList<>();

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "success", nullable = false)
    private Boolean success;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
}
