package com.example.umc9th.domain.mission.dto.res;

import com.example.umc9th.domain.mission.entity.Mission;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class MissionResDTO {

    @Getter
    @Builder
    public static class MyMissionWithSuccess {
        private final Long missionId;
        private final String restaurantName;
        private final String restaurantLocation;
        private final Boolean success;
        private final Integer point;
    }

    @Getter
    @Builder
    public static class MyMissionWithLocation {
        private final Long missionId;
        private final String restaurantName;
        private final String restaurantLocation;
        private final Boolean success;
        private final Integer point;
        private final LocalDate endDate;
    }

    @Getter
    @Builder
    public static class newMyMission {
        private final Long missionId;
        private final Boolean challenge;
    }
}
