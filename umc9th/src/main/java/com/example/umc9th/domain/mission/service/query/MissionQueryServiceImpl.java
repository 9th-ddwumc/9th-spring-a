package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
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


}
