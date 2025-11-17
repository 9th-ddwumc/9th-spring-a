package com.example.umc9th_chapter4.domain.store.converter;

import com.example.umc9th_chapter4.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th_chapter4.domain.store.dto.res.StoreResDTO;
import com.example.umc9th_chapter4.domain.store.entity.Store;
import com.example.umc9th_chapter4.domain.store.entity.Location;

public class StoreConverter {

    public static Store toStore(StoreReqDTO.StoreCreateDTO dto, Location location) {
        return Store.builder()
                .location(location)
                .name(dto.name())
                .managerCode(dto.managerCode())
                .detailAddress(dto.detailAddress())
                .build();
    }

    public static StoreResDTO.StoreCreateDTO toCreateDTO(Store store) {
        return StoreResDTO.StoreCreateDTO.builder()
                .storeId(store.getId())
                .locationId(store.getLocation().getId())
                .name(store.getName())
                .managerCode(store.getManagerCode())
                .detailAddress(store.getDetailAddress())
                .build();
    }
}
