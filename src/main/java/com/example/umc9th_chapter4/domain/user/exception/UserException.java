package com.example.umc9th_chapter4.domain.user.exception;

import com.example.umc9th_chapter4.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th_chapter4.global.apiPayload.exception.GeneralException;

public class UserException extends GeneralException {
    public UserException(BaseErrorCode code) {
        super(code);
    }
}
