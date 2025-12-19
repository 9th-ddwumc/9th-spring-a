package com.example.umc9th.domain.restraunt.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RestaurantErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "RESTAURANT404_1", "존재하지 않는 가게입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
