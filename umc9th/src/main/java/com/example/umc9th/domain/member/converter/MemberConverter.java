package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.req.MemberReqDto;
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

    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    public static Member toMember(
            MemberReqDto.JoinDTO dto
    ) {
        return Member.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .build();
    }
}
