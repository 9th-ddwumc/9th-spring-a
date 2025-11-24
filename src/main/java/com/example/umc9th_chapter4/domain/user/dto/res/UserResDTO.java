package com.example.umc9th_chapter4.domain.user.dto.res;

import lombok.Builder;
import java.time.LocalDateTime;

public class UserResDTO {
    @Builder
    public record JoinDTO(
            Long userId,
            LocalDateTime createAt
    ){}
}
