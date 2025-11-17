package com.example.umc9th_chapter4.domain.store.service.command;

import com.example.umc9th_chapter4.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th_chapter4.domain.store.dto.res.StoreResDTO;

public interface StoreCommandService {
    StoreResDTO.StoreCreateDTO createStore(Long locationId, StoreReqDTO.StoreCreateDTO dto);
}
