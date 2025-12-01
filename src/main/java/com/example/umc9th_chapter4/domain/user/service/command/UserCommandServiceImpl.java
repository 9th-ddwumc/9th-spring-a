package com.example.umc9th_chapter4.domain.user.service.command;

import com.example.umc9th_chapter4.domain.food.exception.FoodException;
import com.example.umc9th_chapter4.domain.food.exception.code.FoodErrorCode;
import com.example.umc9th_chapter4.domain.food.repository.FoodRepository;
import com.example.umc9th_chapter4.domain.user.converter.UserConverter;
import com.example.umc9th_chapter4.domain.user.dto.req.UserReqDTO;
import com.example.umc9th_chapter4.domain.user.dto.res.UserResDTO;
import com.example.umc9th_chapter4.domain.user.entity.Users;
import com.example.umc9th_chapter4.domain.user.entity.UserFood;
import com.example.umc9th_chapter4.domain.user.enums.Role;
import com.example.umc9th_chapter4.domain.user.repository.UserFoodRepository;
import com.example.umc9th_chapter4.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final UserFoodRepository userFoodRepository;
    private final FoodRepository foodRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    @Transactional
    public UserResDTO.JoinDTO signup(
            UserReqDTO.JoinDTO dto
    ){
        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(dto.password());

        // 사용자 생성: 유저 / 관리자는 따로 API 만들어서 관리
        Users member = UserConverter.toUser(dto, salt, Role.ROLE_USER);
        // DB 적용
        userRepository.save(member);

        // 선호 음식 존재 여부 확인
        if (dto.preferCategory().size() > 1) {
            List<UserFood> memberFood = dto.preferCategory().stream()
                    .map(id -> UserFood.builder()
                            .member(member)
                            .food(foodRepository.findById(id)
                                    .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)))
                            .build()
                    )
                    .collect(Collectors.toList());

            userFoodRepository.saveAll(memberFood);
        }


        // 응답 DTO 생성
        return UserConverter.toJoinDTO(member);
    }
}
