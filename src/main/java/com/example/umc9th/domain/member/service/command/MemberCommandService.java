package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.req.MemberReqDto;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;

public interface MemberCommandService {
    MemberResDTO.JoinDTO signUp(
            MemberReqDto.JoinDTO dto
    );
}
