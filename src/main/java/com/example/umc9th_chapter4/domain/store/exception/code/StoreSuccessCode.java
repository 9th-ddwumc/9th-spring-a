package com.example.umc9th_chapter4.domain.store.exception.code;

import com.example.umc9th_chapter4.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED,
            "STORE201_1",
            "가게가 성공적으로 등록되었습니다."),

    FOUND(HttpStatus.OK,
            "STORE200_1",
            "성공적으로 가게를 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
