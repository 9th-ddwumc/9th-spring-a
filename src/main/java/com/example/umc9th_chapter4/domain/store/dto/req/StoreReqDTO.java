package com.example.umc9th_chapter4.domain.store.dto.req;

public class StoreReqDTO {

    // 가게 생성 요청 DTO
    public record StoreCreateDTO(
            String name,
            Long managerCode,
            String detailAddress
    ) {}
}
