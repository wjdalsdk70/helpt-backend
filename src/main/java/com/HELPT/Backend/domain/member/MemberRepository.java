package com.HELPT.Backend.domain.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import static com.HELPT.Backend.domain.member.QMember.member;

import java.util.List;

@Repository
@Transactional
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;
    private static JPAQueryFactory queryFactory;
    @Autowired
    public void setJPAQueryFactory(EntityManager entityManager) {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    public Member findByToken(String tk)
    {
        return queryFactory.selectFrom(member)
                .where(member.accessToken.eq(tk))
                .fetchOne();
    }

    public Member add(Member member)
    {
        em.persist(member);
        return member;
    }
}
