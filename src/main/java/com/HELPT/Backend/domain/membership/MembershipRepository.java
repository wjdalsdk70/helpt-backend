package com.HELPT.Backend.domain.membership;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import static com.HELPT.Backend.domain.membership.QMembership.membership;

import java.util.List;

@Repository
@Transactional
public class MembershipRepository {

    @PersistenceContext
    private EntityManager em;
    private static JPAQueryFactory queryFactory;
    @Autowired
    public void setJPAQueryFactory(EntityManager entityManager) {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    public Membership findByUserid(int userid)
    {
        return queryFactory.selectFrom(membership)
                .where(membership.userId.eq(userid))
                .fetchOne();
    }

}
