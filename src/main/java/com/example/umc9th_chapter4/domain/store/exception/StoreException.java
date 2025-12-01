package com.example.umc9th_chapter4.domain.store.exception;

import com.example.umc9th_chapter4.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th_chapter4.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {

    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
