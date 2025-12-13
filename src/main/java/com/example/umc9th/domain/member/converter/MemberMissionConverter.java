package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.stereotype.Component;

@Component
public class MemberMissionConverter {

    public MemberMission toChallengeMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();
    }

    public MemberMissionResDTO.ChallengeMemberMission toChallengeMemberMission(MemberMission challengeMemberMission) {
        return MemberMissionResDTO.ChallengeMemberMission.builder()
                .memberMissionId(challengeMemberMission.getId())
                .challenge(true)
                .build();
    }

}
