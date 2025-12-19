package com.example.umc9th.domain.member.entity.mapping;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.MemberMissionStatus;
import com.example.umc9th.domain.member.exception.MemberMissionException;
import com.example.umc9th.domain.member.exception.code.MemberMissionErrorCode;
import com.example.umc9th.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_mission")
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MemberMissionStatus status;

    public void completeMission() {
        if (this.status == MemberMissionStatus.COMPLETED) {
            throw new MemberMissionException(MemberMissionErrorCode.INVALID_STATUS_CHANGE);
        }
        this.status = MemberMissionStatus.COMPLETED;
    }
}
