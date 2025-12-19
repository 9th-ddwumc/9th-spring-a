package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.member.enums.MemberMissionStatus;
import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
public interface MissionControllerDocs {

    @Operation(
            summary = "가게의 미션 목록 조회 API By 레미 (개발 완료)",
            description = "특정 가게의 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/missions")
    ApiResponse<MissionResDTO.MissionPreViewListDTO> getMissions(
            @RequestParam("restaurantName") String restaurantName,
            @RequestParam("page") @ValidPage Integer page
    );

    @Operation(
            summary = "내가 진행중인 미션 목록 조회 API By 레미 (개발 완료)",
            description = "내가 진행중인 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/{memberId}/mymissions")
    ApiResponse<MissionResDTO.MyMissionPreViewListDTO> getMyMissions(
            @PathVariable("memberId") Long memberId,
            @RequestParam("status") MemberMissionStatus status,
            @RequestParam("page") @ValidPage Integer page
    );

    @Operation(
            summary = "진행중인 미션 진행 완료로 바꾸기 API By 레미 (개발 완료)",
            description = "진행중인 미션을 진행완료로 변경합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/mymissions/complete")
    ApiResponse<MissionResDTO.MyMissionPreViewListDTO> completeMyMissions(
            @RequestBody @Valid MissionReqDTO.CompleteMyMissionDTO request,
            @RequestParam("page") @ValidPage Integer page
    );
}
