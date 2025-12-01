package com.example.umc9th_chapter4.domain.user.controller;

import com.example.umc9th_chapter4.domain.user.dto.req.UserReqDTO;
import com.example.umc9th_chapter4.domain.user.dto.res.UserResDTO;
import com.example.umc9th_chapter4.domain.user.exception.code.UserSuccessCode;
import com.example.umc9th_chapter4.domain.user.service.command.UserCommandService;
import com.example.umc9th_chapter4.domain.user.service.query.MemberQueryService;
import com.example.umc9th_chapter4.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;
    private final MemberQueryService memberQueryService;

    @Operation(
            summary = "회원가입 API By 포롱 (개발중)",
            description = "새로운 사용자를 등록합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "입력 값 오류")
    })
    @PostMapping("/sign-up")
    public ApiResponse<UserResDTO.JoinDTO> signUp(
            @RequestBody @Valid UserReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, userCommandService.signup(dto));
    }

    @Operation(
            summary = "로그인 API",
            description = "이메일과 비밀번호로 로그인하여 JWT Access Token을 받습니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "로그인 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "입력 값 오류")
    })
    @PostMapping("/login")
    public ApiResponse<UserResDTO.LoginDTO> login(
            @RequestBody @Valid UserReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, memberQueryService.login(dto));
    }
}
