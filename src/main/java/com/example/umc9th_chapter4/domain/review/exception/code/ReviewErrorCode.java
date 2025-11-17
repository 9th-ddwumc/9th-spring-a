package com.example.umc9th_chapter4.domain.review.exception.code;

import com.example.umc9th_chapter4.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "해당 리뷰를 찾을 수 없습니다."),

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_2",
            "리뷰를 작성할 가게를 찾을 수 없습니다."),

    MOCK_USER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_3",
            "테스트용(하드코딩) 유저를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
