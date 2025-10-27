package com.example.umc9th_chapter4.domain.user.entity;

import com.example.umc9th_chapter4.domain.mission.entity.UserMission;
import com.example.umc9th_chapter4.domain.review.entity.Review;
import com.example.umc9th_chapter4.domain.user.enums.Gender;
import com.example.umc9th_chapter4.domain.user.enums.SocialType;
import com.example.umc9th_chapter4.global.jpa.BaseTimeEntity;
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
@Table(name = "users")
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = false, length = 20)
    private SocialType socialType;

    @Column(name = "social_id", nullable = false, length = 50)
    private String socialId;

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 10)
    @Builder.Default
    private Gender gender = Gender.OTHER;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    // 양방향: Users(1) : UserMission(N)
    @Builder.Default
    @OneToMany(mappedBy = "users")
    private List<UserMission> userMissions = new ArrayList<>();

    // 양방향: Users(1) : Review(N)
    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @PrePersist
    void prePersist() {
        if (gender == null) gender = Gender.OTHER;
        if (point == null) point = 0;
    }
}
