package com.example.umc9th_chapter4.domain.user.service.command;

import com.example.umc9th_chapter4.domain.user.dto.req.UserReqDTO;
import com.example.umc9th_chapter4.domain.user.dto.res.UserResDTO;

public interface UserCommandService {
    // 회원가입
    UserResDTO.JoinDTO signup(
            UserReqDTO.JoinDTO dto
    );
}
