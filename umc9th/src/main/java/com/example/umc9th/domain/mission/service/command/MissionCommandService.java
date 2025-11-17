package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.member.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;

public interface MissionCommandService {
    MemberMissionResDTO.ChallengeMemberMission challengeMission(MissionReqDTO.challengeMissionDTO request);

}
