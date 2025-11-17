package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.entity.Mission;

public class MissionDto {

    private final Mission mission;
    private final String restaurantName;
    private final String restaurantLocation;

    public MissionDto(Mission mission, String restaurantName, String restaurantLocation) {
        this.mission = mission;
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
    }
}
