package com.example.umc9th.domain.member.service.query;

import com.example.umc9th.domain.member.dto.req.MemberReqDto;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import jakarta.validation.Valid;

public interface MemberQueryService {
    MemberResDTO.MyPage getMypage(Long memberId);

    MemberResDTO.LoginDTO login(MemberReqDto.LoginDTO dto);
}
