package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.member.converter.MemberMissionConverter;
import com.example.umc9th.domain.member.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberMissionRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberMissionConverter memberMissionConverter;


    @Override
    public MemberMissionResDTO.ChallengeMemberMission challengeMission(MissionReqDTO.challengeMissionDTO request)  {

//        Member member = memberRepository.findById(request.getMemberId())
//                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
//
//        Mission mission = missionRepository.findById(request.getMissionId())
//                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        Member member = memberRepository.getReferenceById(request.getMemberId());
        Mission mission = missionRepository.getReferenceById(request.getMissionId());

        MemberMission challengeMemberMission = memberMissionConverter.toChallengeMemberMission(member, mission);
        memberMissionRepository.save(challengeMemberMission);

        return memberMissionConverter.toChallengeMemberMission(challengeMemberMission);
    }
}
