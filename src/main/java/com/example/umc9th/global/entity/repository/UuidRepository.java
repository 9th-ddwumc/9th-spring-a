package com.example.umc9th.global.entity.repository;

import com.example.umc9th.global.entity.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
}
