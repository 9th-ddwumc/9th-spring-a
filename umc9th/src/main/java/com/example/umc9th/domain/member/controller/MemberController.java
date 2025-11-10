package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.service.query.MemberQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberQueryService memberQueryService;


    @GetMapping("/{memberId}/mypage")
    public ApiResponse<MemberResDTO.MyPage> showMyPage(@PathVariable("memberId") Long memberId) {
        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code,
                memberQueryService.getMypage(memberId)
        );
    }
}
