package com.example.umc9th_chapter4.domain.user.converter;

import com.example.umc9th_chapter4.domain.user.dto.req.UserReqDTO;
import com.example.umc9th_chapter4.domain.user.dto.res.UserResDTO;
import com.example.umc9th_chapter4.domain.user.entity.Users;
import com.example.umc9th_chapter4.domain.user.enums.SocialType;

public class UserConverter {

    // Entity -> DTO
    public static UserResDTO.JoinDTO toJoinDTO(Users user) {
        return UserResDTO.JoinDTO.builder()
                .userId(user.getUserId())
                .createAt(user.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Users toUser(UserReqDTO.JoinDTO dto) {

        return Users.builder()
                .socialType(SocialType.KAKAO)
                .socialId("TEMP_SOCIAL_ID")
                .email("temp@example.com")
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .build();
    }
}
