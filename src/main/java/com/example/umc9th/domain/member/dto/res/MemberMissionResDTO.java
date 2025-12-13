package com.example.umc9th.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;

public class MemberMissionResDTO {

    @Builder
    @Getter
    public static class ChallengeMemberMission {
        private Long memberMissionId;
        private Boolean challenge;
    }
}
