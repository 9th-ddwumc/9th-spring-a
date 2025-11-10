package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.MissionDto;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    //커서 존재O
    @Query(
            "select m from Mission m " +
                    "join m.memberMissions mm " +
                    "where mm.member.id = :memberId " +
                    "and m.success = :success " +
                    "and (" +
                    "m.endDate < :lastEndDate " +
                    "or (m.endDate = :lastEndDate and m.id < :lastId) " +
                    ") " +
                    "order by m.endDate desc, m.id desc"

    )
    List<Mission> findMissionByCursor(@Param("memberId") Long memberId,
                                      @Param("success") Boolean success,
                                      @Param("lastEndDate") LocalDate lastEndDate,
                                      @Param("lastId") Long lastId,
                                      Pageable pageable);

    //커서 존재x -> 최초 조회
    @Query(
            "select m from Mission m " +
                    "join m.memberMissions mm " +
                    "where mm.member.id = :memberId " +
                    "and m.success = :success " +
                    "order by m.endDate desc, m.id desc"
    )
    List<Mission> findFirstMission(@Param("memberId") Long memberId,
                                   @Param("success") Boolean success,
                                   Pageable pageable);


    //커서 존재0
    @Query(
            "select new com.example.umc9th.domain.mission.dto.MissionDto(m, r.name, r.location) " +
                    "from Mission m " +
                    "join m.restaurant r " +
                    "join m.memberMissions mm " +
                    "where mm.member.id = :memberId " +
                    "and r.location = :location " +
                    "and m.endDate > current_date() " +
                    "and m.success = false " +
                    "and (" +
                    "m.endDate < :endDate " +
                    "or (m.endDate = :endDate and m.id < :lastId)" +
                    ")"+
                    "order by m.endDate desc, m.id desc"
    )
    List<Mission> findByLocationWithCursor(@Param("memberId") Long memberId,
                                               @Param("location") String location,
                                               @Param("endDate") LocalDate endDate,
                                               @Param("lastId") Long lastId,
                                               Pageable pageable);

    //커서 존재x -> 최초 조회
    @Query(
            "select new com.example.umc9th.domain.mission.dto.MissionDto(m, r.name, r.location) " +
                    "from Mission m " +
                    "join m.restaurant r " +
                    "join m.memberMissions mm " +
                    "where mm.member.id = :memberId " +
                    "and r.location = :location " +
                    "and m.endDate > current_date() " +
                    "and m.success = false " +
                    "order by m.endDate desc, m.id desc"
    )
    List<Mission> findByLocationFirst(@Param("memberId") Long memberId,
                                         @Param("location") String location,
                                         Pageable pageable);
}
