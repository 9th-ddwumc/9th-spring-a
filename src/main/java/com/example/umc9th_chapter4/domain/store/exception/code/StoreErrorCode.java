package com.example.umc9th_chapter4.domain.store.exception.code;

import com.example.umc9th_chapter4.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_1",
            "해당 가게를 찾을 수 없습니다."),

    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_2",
            "가게를 등록할 지역을 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
