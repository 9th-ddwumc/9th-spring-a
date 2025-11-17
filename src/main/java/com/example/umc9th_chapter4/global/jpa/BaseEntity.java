package com.example.umc9th_chapter4.global.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    // 생성 시 자동 입력
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    protected LocalDateTime createdAt;

    // 수정 시 자동 갱신
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    protected LocalDateTime updatedAt;

    // 소프트 삭제용
    @Column(name = "deleted_at")
    protected LocalDateTime deletedAt;

    public void markDeleted() {
        this.deletedAt = LocalDateTime.now();
    }
}
