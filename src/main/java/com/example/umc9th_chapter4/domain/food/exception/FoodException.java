package com.example.umc9th_chapter4.domain.food.exception;

import com.example.umc9th_chapter4.domain.food.exception.code.FoodErrorCode;
import com.example.umc9th_chapter4.global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {

    public FoodException(FoodErrorCode errorCode) {
        super(errorCode);
    }
}
