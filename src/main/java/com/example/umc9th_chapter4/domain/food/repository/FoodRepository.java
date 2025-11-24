package com.example.umc9th_chapter4.domain.food.repository;

import com.example.umc9th_chapter4.domain.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
