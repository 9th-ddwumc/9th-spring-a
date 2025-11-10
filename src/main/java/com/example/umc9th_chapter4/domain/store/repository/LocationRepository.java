package com.example.umc9th_chapter4.domain.store.repository;

import com.example.umc9th_chapter4.domain.store.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findByName(String name);

    @Query("select count(s) from com.example.umc9th_chapter4.domain.store.entity.Store s where s.location.id = :locationId")
    long countStores(Long locationId);
}
