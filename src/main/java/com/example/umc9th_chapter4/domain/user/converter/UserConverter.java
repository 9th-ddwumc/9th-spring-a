package com.example.umc9th_chapter4.domain.user.converter;

import com.example.umc9th_chapter4.domain.user.dto.req.UserReqDTO;
import com.example.umc9th_chapter4.domain.user.dto.res.UserResDTO;
import com.example.umc9th_chapter4.domain.user.entity.Users;
import com.example.umc9th_chapter4.domain.user.enums.Role;
import com.example.umc9th_chapter4.domain.user.enums.SocialType;

public class UserConverter {

    // Entity -> DTO
    public static UserResDTO.JoinDTO toJoinDTO(Users user) {
        return UserResDTO.JoinDTO.builder()
                .userId(user.getUserId())
                .createAt(user.getCreatedAt())
                .build();
    }

    // DTO, Salted Password, Role -> Entity
    public static Users toUser(
            UserReqDTO.JoinDTO dto,
            String password,
            Role role
    ) {
        return Users.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .birth(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .socialId("LOCAL")
                .socialType(SocialType.LOCAL)
                .build();
    }

    // Entity, AccessToken -> LoginDTO
    public static UserResDTO.LoginDTO toLoginDTO(Users user, String accessToken) {
        return UserResDTO.LoginDTO.builder()
                .userId(user.getUserId())
                .accessToken(accessToken)
                .build();
    }
}
