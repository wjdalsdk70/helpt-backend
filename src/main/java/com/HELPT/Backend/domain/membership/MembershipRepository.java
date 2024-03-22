package com.HELPT.Backend.domain.membership;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MembershipRepository {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @Autowired
    public void setEmf(EntityManagerFactory entityManagerFactory) {
        emf = entityManagerFactory;
        em = emf.createEntityManager();
    }


    public Membership checkMembership(int userid)
    {
            List<Membership> resultList = em.createQuery("SELECT m FROM Membership m WHERE userId=:userid",Membership.class)
                    .setParameter("userid",userid)
                    .getResultList();
            return resultList.isEmpty()?null:resultList.get(0);
    }

}
