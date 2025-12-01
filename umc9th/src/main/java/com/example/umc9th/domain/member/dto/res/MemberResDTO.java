package com.example.umc9th.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    @Getter
    public static class MyPage {
        private String name;
        private String email;
        private String phone;
        private Integer point;
    }

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ) {
    }

    // 로그인
    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}
}
