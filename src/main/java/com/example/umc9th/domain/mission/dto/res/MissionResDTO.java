package com.example.umc9th.domain.mission.dto.res;

import com.example.umc9th.domain.member.enums.MemberMissionStatus;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

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

    @Builder
    public record MissionPreViewListDTO(
            List<MissionPreViewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MissionPreViewDTO(
            String restaurantName,
            Integer point,
            String content,
            LocalDate endDate
    ){}

    @Builder
    public record MyMissionPreViewListDTO(
            List<MyMissionPreViewDTO> myMissionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MyMissionPreViewDTO(
            String restaurantName,
            String content,
            Integer point,
            MemberMissionStatus status
    ){}
}
