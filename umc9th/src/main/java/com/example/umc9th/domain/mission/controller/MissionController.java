package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.member.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.member.enums.MemberMissionStatus;
import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MissionController implements MissionControllerDocs{

    private final MissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;

    @GetMapping("/{memberId}/missions/by-success")
    public ApiResponse<List<MissionResDTO.MyMissionWithSuccess>> getMyMissionBySuccess(
            @PathVariable("memberId") Long memberId,
            @RequestParam Boolean isSuccess,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastEndDate,
            @RequestParam(required = false) Long lastId,
            @RequestParam(defaultValue = "10") int pageSize) {

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code,
                missionQueryService.getMyMissionBySuccess(memberId, isSuccess, lastEndDate, lastId, pageSize)
        );
    }

    @GetMapping("/{memberId}/missions/by-location")
    public ApiResponse<List<MissionResDTO.MyMissionWithLocation>> getMyMissionByLocation(
            @PathVariable("memberId") Long memberId,
            @RequestParam("location") String location,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastEndDate,
            @RequestParam(required = false) Long lastId,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code,
                missionQueryService.getMyMissionByLocation(memberId, location, lastEndDate, lastId, pageSize)
        );
    }

    @PostMapping("/home/challenge")
    public ApiResponse<MemberMissionResDTO.ChallengeMemberMission> challengeMission(
            @RequestBody @Valid MissionReqDTO.challengeMissionDTO request
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.FOUND,
                missionCommandService.challengeMission(request)
        );
    }


    @Override
    public ApiResponse<MissionResDTO.MissionPreViewListDTO> getMissions(String restaurantName, Integer page) {
        MissionSuccessCode code = MissionSuccessCode.FOUND;
        page--;
        return ApiResponse.onSuccess(code, missionQueryService.findRestaurantMission(restaurantName, page));
    }

    @Override
    public ApiResponse<MissionResDTO.MyMissionPreViewListDTO> getMyMissions(Long memberId, MemberMissionStatus status, Integer page) {
        MissionSuccessCode code = MissionSuccessCode.FOUND;
        page--;
        return ApiResponse.onSuccess(code, missionQueryService.findMyMission(memberId, status, page));
    }

    @Override
    public ApiResponse<MissionResDTO.MyMissionPreViewListDTO> completeMyMissions(MissionReqDTO.completeMyMissionDTO request, Integer page) {
        MissionSuccessCode code = MissionSuccessCode.FOUND;
        page--;
        return ApiResponse.onSuccess(code, missionCommandService.completeMission(request, page));
    }

}
