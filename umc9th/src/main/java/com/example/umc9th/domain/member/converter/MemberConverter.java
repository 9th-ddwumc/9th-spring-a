package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {

    public MemberResDTO.MyPage toMyPage(Member member) {
        return MemberResDTO.MyPage.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .point(member.getPoint())
                .build();
    }

}
