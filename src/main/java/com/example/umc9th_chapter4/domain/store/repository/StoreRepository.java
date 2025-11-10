package com.example.umc9th_chapter4.domain.store.repository;

import com.example.umc9th_chapter4.domain.store.entity.Store;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    // 지역명으로 스토어 조회
    List<Store> findByLocation_Name(String locationName);

    // 이름 검색 + 페이징
    Page<Store> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

    // 스토어 + 리뷰를 한번에
    @Query("select distinct s from Store s left join fetch s.reviews where s.id = :storeId")
    Store findWithReviewsById(Long storeId);

    // 대안: 엔티티그래프
    @EntityGraph(attributePaths = {"reviews"})
    List<Store> findByLocation_Id(Long locationId);
}
