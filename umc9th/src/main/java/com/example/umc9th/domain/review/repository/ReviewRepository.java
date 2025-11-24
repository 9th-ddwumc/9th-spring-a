package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.restraunt.entity.Restaurant;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryDsl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {


    List<Review> findAll();

    @Query("select r.member.name, r.restaurant, r.star, r.content, r.createdAt from Review r")
    List<Review> findAllReviews();


    Page<Review> findAllByRestaurant(Restaurant restaurant, PageRequest pageRequest);

    Page<Review> findAllByMember(Member member, PageRequest pageRequest);


}
