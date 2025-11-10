package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionQueryService missionQueryService;

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
}
