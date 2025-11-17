package com.example.umc9th_chapter4.domain.mission.repository;

import com.example.umc9th_chapter4.domain.mission.entity.UserMission;
import com.example.umc9th_chapter4.domain.mission.enums.MissionStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 중복 도전 방지
    boolean existsByMission_IdAndUsers_UserId(Long missionId, Long userId);
    // 진행 중/완료 별 페이징
    Page<UserMission> findByUsers_UserIdAndStatusOrderByUpdatedAtDesc(
            Long userId, MissionStatus status, Pageable pageable);

    Page<UserMission> findByUsers_UserIdAndStatusOrderByClearedAtDesc(
            Long userId, MissionStatus status, Pageable pageable);

    // 진행중 + 완료 묶음(IN) 페이징
    Page<UserMission> findByUsers_UserIdAndStatusInOrderByUpdatedAtDesc(
            Long userId, List<MissionStatus> statuses, Pageable pageable);

    // 상세 조회 시 N+1 회피 (fetch join)
    @Query("select um from UserMission um " +
            "join fetch um.users u " +
            "join fetch um.mission m " +
            "where u.userId = :userId and um.status = :status")
    List<UserMission> findDetailByUserAndStatus(Long userId, MissionStatus status);

    // 대안: 엔티티 그래프
    @EntityGraph(attributePaths = {"users", "mission"})
    List<UserMission> findByUsers_UserId(Long userId);

    // 상태 업데이트 (@Modifying) — 서비스에서 @Transactional 로 호출
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update UserMission um set um.status = :status where um.id = :id")
    int updateStatusById(Long id, MissionStatus status);
}
