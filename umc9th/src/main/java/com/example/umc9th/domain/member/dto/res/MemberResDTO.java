package com.example.umc9th.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {

    @Builder
    @Getter
    public static class MyPage {
        private String name;
        private String email;
        private String phone;
        private Integer point;
    }
}
