package com.example.umc9th_chapter4.domain.mission.repository;

import com.example.umc9th_chapter4.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 홈 화면: 선택 지역에서 마감 전 미션 (페이징)
    Page<Mission> findByStore_Location_IdAndDeadlineAfterOrderByCreatedAtDesc(
            Long locationId, LocalDate now, Pageable pageable
    );

    // 포인트/마감 필터 예시 (@Query)
    @Query("select m from Mission m where m.point >= :minPoint and m.deadline >= :fromDate")
    List<Mission> findAvailableMissions(int minPoint, LocalDate fromDate);

    // 미션 + 유저미션 프리로드 (@EntityGraph)
    @EntityGraph(attributePaths = {"userMissions"})
    @Query("select m from Mission m where m.store.id = :storeId")
    List<Mission> findWithUserMissionsByStoreId(Long storeId);
}
