package com.example.umc9th.domain.mission.dto.req;

import com.example.umc9th.global.annotation.ExistMembers;
import com.example.umc9th.global.annotation.ExistMissions;
import lombok.Builder;
import lombok.Getter;

public class MissionReqDTO {

    @Getter
    @Builder
    public static class challengeMissionDTO {
        @ExistMissions
        private final Long missionId;
        @ExistMembers
        private final Long memberId;
    }

    @Getter
    @Builder
    public static class CompleteMyMissionDTO {
        @ExistMissions
        private final Long missionId;
        @ExistMembers
        private final Long memberId;
    }
}
