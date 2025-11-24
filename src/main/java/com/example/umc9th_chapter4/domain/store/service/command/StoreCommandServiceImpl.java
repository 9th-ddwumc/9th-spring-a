package com.example.umc9th_chapter4.domain.store.service.command;

import com.example.umc9th_chapter4.domain.store.converter.StoreConverter;
import com.example.umc9th_chapter4.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th_chapter4.domain.store.dto.res.StoreResDTO;
import com.example.umc9th_chapter4.domain.store.entity.Location;
import com.example.umc9th_chapter4.domain.store.entity.Store;
import com.example.umc9th_chapter4.domain.store.exception.StoreException;
import com.example.umc9th_chapter4.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th_chapter4.domain.store.repository.LocationRepository;
import com.example.umc9th_chapter4.domain.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final LocationRepository locationRepository;

    @Override
    @Transactional
    public StoreResDTO.StoreCreateDTO createStore(Long locationId, StoreReqDTO.StoreCreateDTO dto) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.LOCATION_NOT_FOUND));

        Store store = StoreConverter.toStore(dto, location);
        storeRepository.save(store);

        return StoreConverter.toCreateDTO(store);
    }

}
