package com.HELPT.Backend.domain.membership;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MembershipRepository {

    @PersistenceContext
    private static EntityManager em;
    private static JPAQueryFactory queryFactory;
    @Autowired
    public void setJPAQueryFactory(EntityManager entityManager) {
        queryFactory = new JPAQueryFactory(entityManager);
    }


    public Membership checkMembership(int userid)
    {
            List<Membership> resultList = em.createQuery("SELECT m FROM Membership m WHERE userId=:userid",Membership.class)
                    .setParameter("userid",userid)
                    .getResultList();
            return resultList.isEmpty()?null:resultList.get(0);
    }

}
