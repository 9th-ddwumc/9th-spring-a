package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.stereotype.Component;

@Component
public class MissionConverter {

    public MissionResDTO.MyMissionWithSuccess toMyMissionWithSuccess(Mission mission) {
        return MissionResDTO.MyMissionWithSuccess.builder()
                .missionId(mission.getId())
                .restaurantName(mission.getRestaurant().getName())
                .restaurantLocation(mission.getRestaurant().getLocation())
                .success(mission.getSuccess())
                .point(mission.getPoint())
                .build();
    }

    public MissionResDTO.MyMissionWithLocation toMyMissionWithLocation(Mission mission) {
        return MissionResDTO.MyMissionWithLocation.builder()
                .missionId(mission.getId())
                .restaurantName(mission.getRestaurant().getName())
                .restaurantLocation(mission.getRestaurant().getLocation())
                .success(mission.getSuccess())
                .point(mission.getPoint())
                .endDate(mission.getEndDate())
                .build();
    }
}
