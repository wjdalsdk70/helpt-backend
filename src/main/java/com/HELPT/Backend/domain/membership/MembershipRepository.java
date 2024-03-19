package com.HELPT.Backend.domain.membership;

import com.HELPT.Backend.domain.jpamodule.callbackclass.JpaModule;
import com.HELPT.Backend.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MembershipRepository {

    public Membership checkMembership(int userid)
    {
        return JpaModule.executeTransaction(em->{
            List<Membership> resultList = em.createQuery("SELECT m FROM Membership m WHERE userid=:userid",Membership.class)
                    .setParameter("userid",userid)
                    .getResultList();
            return resultList.isEmpty()?null:resultList.get(0);
        });
    }

}
