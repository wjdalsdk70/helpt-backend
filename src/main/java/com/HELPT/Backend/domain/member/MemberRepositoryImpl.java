package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.domain.membership.Membership;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.HELPT.Backend.domain.membership.QMembership.membership;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    @PersistenceContext
    private final EntityManager em;

    private static JPAQueryFactory queryFactory;

    @Autowired
    public void setQueryFactory(EntityManager entityManager)
    {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Membership attendance(Long userid)
    {
        return queryFactory.selectFrom(membership)
                .where(membership.userId.eq(userid))
                .fetchOne();
    }
}
