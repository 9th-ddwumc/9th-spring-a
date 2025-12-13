package com.example.umc9th.domain.restraunt.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.restraunt.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findById(Long id);

    Optional<Restaurant> findByName(String name);
}
