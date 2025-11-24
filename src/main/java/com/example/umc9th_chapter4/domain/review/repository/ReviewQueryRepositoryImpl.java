package com.example.umc9th_chapter4.domain.review.repository;

import com.example.umc9th_chapter4.domain.review.dto.MyReviewRowDto;
import com.example.umc9th_chapter4.domain.review.entity.QReview;
import com.example.umc9th_chapter4.domain.review.entity.QReviewReply;
import com.example.umc9th_chapter4.domain.store.entity.QStore;
import com.example.umc9th_chapter4.domain.user.entity.QUsers;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

    private final JPAQueryFactory query;

    // Q타입
    private static final QReview review = QReview.review;
    private static final QReviewReply reply = QReviewReply.reviewReply;
    private static final QStore store = QStore.store;
    private static final QUsers users = QUsers.users;

    @Override
    public Page<MyReviewRowDto> findMyReviews(Long loginUserId, String storeName, Integer ratingBand, Pageable pageable) {

        // 1) where 조건(동적)
        BooleanBuilder where = new BooleanBuilder();
        // Users PK가 userId 라면 아래 그대로, id 라면 users.id.eq(loginUserId) 로 바꿔
        where.and(users.userId.eq(loginUserId));

        if (storeName != null && !storeName.isBlank()) {
            where.and(store.name.eq(storeName));
        }
        if (ratingBand != null) {
            double from = ratingBand.doubleValue();
            double to = from + 1.0; // X점대 => [X.0, X+1.0)
            where.and(review.rating.goe(from).and(review.rating.lt(to)));
        }

        // 2) id만 페이징으로 가져오기 (중복/카운트 안정화를 위해 2단계 페이징)
        List<Long> pageIds = query
                .select(review.id)
                .from(review)
                .join(review.user, users)
                .join(review.store, store)
                .where(where)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (pageIds.isEmpty()) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }

        // 3) total count
        Long total = query
                .select(review.id.count())
                .from(review)
                .join(review.user, users)
                .join(review.store, store)
                .where(where)
                .fetchOne();
        long totalCount = (total == null ? 0 : total);

        // 4) 실제 행 데이터 가져오기 (id IN …, 답글 left join)
        List<MyReviewRowDto> content = query
                .select(
                        com.querydsl.core.types.Projections.constructor(MyReviewRowDto.class,
                                review.id,
                                review.content,
                                review.rating,
                                reply.content  // null 허용
                        )
                )
                .from(review)
                .join(review.user, users)
                .join(review.store, store)
                .leftJoin(review.reply, reply)
                .where(review.id.in(pageIds))
                .orderBy(review.createdAt.desc())
                .fetch();

        return new PageImpl<>(content, pageable, totalCount);
    }
}
