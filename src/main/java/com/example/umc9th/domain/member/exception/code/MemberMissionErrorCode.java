package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBERMISSION404_1",
            "해당 멤버 미션을 찾지 못했습니다."),


    INVALID_STATUS_CHANGE(HttpStatus.BAD_REQUEST,
        "MEMBERMISSION400_2",
            "이미 완료된 미션입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
