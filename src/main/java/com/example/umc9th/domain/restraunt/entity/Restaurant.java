package com.example.umc9th.domain.restraunt.entity;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "restaurant")
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "owner_id", length = 10, nullable = false)
    private String ownerId;

    @Column(name = "addr", length = 100, nullable = false)
    private String address;

    @Column(name = "location", length = 10, nullable = false)
    private String location;
}
