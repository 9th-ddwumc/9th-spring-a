package com.example.umc9th_chapter4.domain.user.service.query;

import com.example.umc9th_chapter4.domain.user.converter.UserConverter;
import com.example.umc9th_chapter4.domain.user.dto.req.UserReqDTO;
import com.example.umc9th_chapter4.domain.user.dto.res.UserResDTO;
import com.example.umc9th_chapter4.domain.user.entity.Users;
import com.example.umc9th_chapter4.domain.user.exception.UserException;
import com.example.umc9th_chapter4.domain.user.exception.code.UserErrorCode;
import com.example.umc9th_chapter4.domain.user.repository.UserRepository;
import com.example.umc9th_chapter4.global.security.CustomUserDetails;
import com.example.umc9th_chapter4.global.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResDTO.LoginDTO login(UserReqDTO.LoginDTO dto) {
        // 사용자 조회
        Users user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new UserException(UserErrorCode.NOT_FOUND);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(user);

        // 액세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return UserConverter.toLoginDTO(user, accessToken);
    }
}
