package com.example.umc9th_chapter4.domain.mission.exception.code;

import com.example.umc9th_chapter4.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "해당 미션을 찾을 수 없습니다."),

    MOCK_USER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_2",
            "테스트용(하드코딩) 유저를 찾을 수 없습니다."),

    ALREADY_CHALLENGED(HttpStatus.BAD_REQUEST,
            "MISSION400_1",
            "이미 도전 중인 미션입니다."),

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_2",
            "미션을 등록할 가게를 찾을 수 없습니다."),

    USER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_3",
            "해당 미션 진행을 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
