package com.example.umc9th_chapter4.domain.user.service.query;

import com.example.umc9th_chapter4.domain.user.dto.req.UserReqDTO;
import com.example.umc9th_chapter4.domain.user.dto.res.UserResDTO;

public interface MemberQueryService {
    UserResDTO.LoginDTO login(UserReqDTO.LoginDTO dto);
}
