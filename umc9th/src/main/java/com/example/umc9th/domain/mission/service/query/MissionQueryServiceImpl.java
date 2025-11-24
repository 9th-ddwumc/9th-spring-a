package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.enums.MemberMissionStatus;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberMissionRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.restraunt.entity.Restaurant;
import com.example.umc9th.domain.restraunt.exception.RestaurantException;
import com.example.umc9th.domain.restraunt.exception.code.RestaurantErrorCode;
import com.example.umc9th.domain.restraunt.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService{

    private final MissionRepository missionRepository;
    private final MissionConverter missionConverter;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public List<MissionResDTO.MyMissionWithSuccess> getMyMissionBySuccess(
            Long memberId, Boolean success, LocalDate lastEndDate,
            Long lastId, int pageSize) {

        List<Mission> myMissions = new ArrayList<>();

        Pageable pageable = PageRequest.of(0, pageSize);

        if (lastEndDate != null && lastId != null) {
            myMissions =  missionRepository.findMissionByCursor(memberId, success, lastEndDate, lastId, pageable);
        } else {
            myMissions =  missionRepository.findFirstMission(memberId, success, pageable);
        }

        List<MissionResDTO.MyMissionWithSuccess> result = new ArrayList<>();

        for (Mission myMission : myMissions) {
            MissionResDTO.MyMissionWithSuccess myMissionDTO = missionConverter.toMyMissionWithSuccess(myMission);
            result.add(myMissionDTO);
        }
        return result;
    }

    @Override
    public List<MissionResDTO.MyMissionWithLocation> getMyMissionByLocation(
            Long memberId,
            String location,
            LocalDate endDate,
            Long lastId,
            int pageSize
    ) {

        List<Mission> myMissions = new ArrayList<>();

        Pageable pageable = PageRequest.of(0, pageSize);

        if (endDate != null && lastId != null) {
            myMissions =  missionRepository.findByLocationWithCursor(memberId, location, endDate, lastId, pageable);
        } else {
            myMissions =  missionRepository.findByLocationFirst(memberId, location, pageable);
        }

        List<MissionResDTO.MyMissionWithLocation> result = new ArrayList<>();

        for (Mission myMission : myMissions) {
            MissionResDTO.MyMissionWithLocation myMissionDTO = missionConverter.toMyMissionWithLocation(myMission);
            result.add(myMissionDTO);
        }
        return result;
    }

    @Override
    public MissionResDTO.MissionPreViewListDTO findRestaurantMission(String restaurantName, Integer page) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName)
                .orElseThrow(() -> new RestaurantException(RestaurantErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> result = missionRepository.findAllByRestaurant(restaurant, pageRequest);

        return MissionConverter.toMissionPreviewListDTO(result);
    }

    @Override
    public MissionResDTO.MyMissionPreViewListDTO findMyMission(Long memberId, MemberMissionStatus status, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<MemberMission> result = memberMissionRepository.findByMemberAndStatus(member, status, pageRequest);

        return MissionConverter.toMyMissionPreviewListDTO(result);
    }


}
