package com.example.umc9th_chapter4.domain.mission.service.command;

import com.example.umc9th_chapter4.domain.mission.converter.MissionConverter;
import com.example.umc9th_chapter4.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th_chapter4.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_chapter4.domain.mission.entity.Mission;
import com.example.umc9th_chapter4.domain.mission.entity.UserMission;
import com.example.umc9th_chapter4.domain.mission.exception.MissionException;
import com.example.umc9th_chapter4.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th_chapter4.domain.mission.repository.MissionRepository;
import com.example.umc9th_chapter4.domain.mission.repository.UserMissionRepository;
import com.example.umc9th_chapter4.domain.store.entity.Store;
import com.example.umc9th_chapter4.domain.store.repository.StoreRepository;
import com.example.umc9th_chapter4.domain.user.entity.Users;
import com.example.umc9th_chapter4.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    // 로그인 대신 사용하는 하드코딩 유저
    private static final Long MOCK_USER_ID = 1L;

    @Override
    @Transactional
    public MissionResDTO.ChallengeDTO challengeMission(Long missionId) {

        // 1. 하드코딩 유저 조회
        Users user = userRepository.findById(MOCK_USER_ID)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MOCK_USER_NOT_FOUND));

        // 2. 미션 조회
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        // 3. 이미 도전 중인지 확인
        if (userMissionRepository.existsByMission_IdAndUsers_UserId(missionId, MOCK_USER_ID)) {
            throw new MissionException(MissionErrorCode.ALREADY_CHALLENGED);
        }

        // 4. UserMission 생성 & 저장
        UserMission userMission = MissionConverter.toUserMission(mission, user);
        userMissionRepository.save(userMission);

        // 5. 응답 DTO 반환
        return MissionConverter.toChallengeDTO(userMission);
    }

    @Override
    @Transactional
    public MissionResDTO.MissionCreateDTO createMission(Long storeId, MissionReqDTO.MissionCreateDTO dto) {

        // 1. 가게 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.STORE_NOT_FOUND));

        // 2. 미션 엔티티 생성
        Mission mission = MissionConverter.toMission(dto, store);

        // 3. 저장
        missionRepository.save(mission);

        // 4. 응답 DTO 변환
        return MissionConverter.toMissionCreateDTO(mission);
    }
}
