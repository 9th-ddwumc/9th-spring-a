package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.member.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;

public interface MissionCommandService {
    MemberMissionResDTO.ChallengeMemberMission challengeMission(MissionReqDTO.challengeMissionDTO request);

    MissionResDTO.MyMissionPreViewListDTO completeMission(MissionReqDTO.CompleteMyMissionDTO request, Integer page);
}
