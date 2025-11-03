package com.example.umc9th_chapter4.domain.user.repository;

import com.example.umc9th_chapter4.domain.user.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserIdAndDeletedAtIsNull(Long userId);

    // 이름 검색 + 최신순 무한스크롤
    Slice<Users> findByNameContainingOrderByCreatedAtDesc(String name, Pageable pageable);

    // 리뷰까지 프리로드
    @EntityGraph(attributePaths = {"reviews"})
    @Query("select u from Users u where u.userId = :userId")
    Users findWithReviews(Long userId);
}
