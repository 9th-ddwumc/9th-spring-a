package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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

    public static MissionResDTO.MissionPreViewListDTO toMissionPreviewListDTO(
            Page<Mission> result
    ){
        return MissionResDTO.MissionPreViewListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMissionPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.MissionPreViewDTO toMissionPreviewDTO(
            Mission mission
    ){
        return MissionResDTO.MissionPreViewDTO.builder()
                .restaurantName(mission.getRestaurant().getName())
                .content(mission.getContent())
                .point(mission.getPoint())
                .endDate(mission.getEndDate())
                .build();
    }

    public static MissionResDTO.MyMissionPreViewListDTO toMyMissionPreviewListDTO(
            Page<MemberMission> result
    ){
        return MissionResDTO.MyMissionPreViewListDTO.builder()
                .myMissionList(result.getContent().stream()
                        .map(MissionConverter::toMyMissionPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.MyMissionPreViewDTO toMyMissionPreviewDTO(
            MemberMission memberMission
    ){
        return MissionResDTO.MyMissionPreViewDTO.builder()
                .restaurantName(memberMission.getMission().getRestaurant().getName())
                .content(memberMission.getMission().getContent())
                .point(memberMission.getMission().getPoint())
                .status(memberMission.getStatus())
                .build();
    }

}
