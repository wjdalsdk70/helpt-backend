package com.HELPT.Backend.domain.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MemberRepository {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @Autowired
    public void setEmf(EntityManagerFactory entityManagerFactory) {
        emf = entityManagerFactory;
        em = emf.createEntityManager();
    }

    public Member checkMember(String tk)
    {
            List<Member> resultList = em.createQuery("SELECT m FROM Member m WHERE accessToken=:tk", Member.class)
                    .setParameter("tk", tk)
                    .getResultList();
            return resultList.isEmpty() ? null : resultList.get(0);
    }

    public Member add(Member member)
    {
        em.persist(member);
        return member;
    }
}
