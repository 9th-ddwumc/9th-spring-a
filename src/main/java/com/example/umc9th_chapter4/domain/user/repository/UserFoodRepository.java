package com.example.umc9th_chapter4.domain.user.repository;

import com.example.umc9th_chapter4.domain.user.entity.UserFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFoodRepository extends JpaRepository<UserFood, Long> {
}
