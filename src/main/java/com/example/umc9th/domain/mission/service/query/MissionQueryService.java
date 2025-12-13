package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.member.enums.MemberMissionStatus;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface MissionQueryService {
    List<MissionResDTO.MyMissionWithSuccess> getMyMissionBySuccess(
            Long memberId,
            Boolean success,
            LocalDate lastEndDate,
            Long lastId,
            int pageSize);

    List<MissionResDTO.MyMissionWithLocation> getMyMissionByLocation(
            Long memberId,
            String location,
            LocalDate endDate,
            Long lastId,
            int pageSize);

    MissionResDTO.MissionPreViewListDTO findRestaurantMission(String restaurantName, Integer page);

    MissionResDTO.MyMissionPreViewListDTO findMyMission(Long memberId, MemberMissionStatus status, Integer page);
}
