package com.example.umc9th_chapter4.domain.store.dto.res;

import lombok.Builder;

public class StoreResDTO {

    // 가게 생성 응답 DTO
    @Builder
    public record StoreCreateDTO(
            Long storeId,
            Long locationId,
            String name,
            Long managerCode,
            String detailAddress
    ) {}
}
