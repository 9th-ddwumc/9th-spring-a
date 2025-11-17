package com.example.umc9th_chapter4.domain.user.controller;

import com.example.umc9th_chapter4.domain.user.dto.req.UserReqDTO;
import com.example.umc9th_chapter4.domain.user.dto.res.UserResDTO;
import com.example.umc9th_chapter4.domain.user.exception.code.UserSuccessCode;
import com.example.umc9th_chapter4.domain.user.service.command.UserCommandService;
import com.example.umc9th_chapter4.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<UserResDTO.JoinDTO> signUp(
            @RequestBody @Valid UserReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, userCommandService.signup(dto));
    }
}
