package com.example.umc9th_chapter4.domain.mission.exception;

import com.example.umc9th_chapter4.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th_chapter4.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {

    public MissionException(BaseErrorCode code) {
        super(code);
    }
}
