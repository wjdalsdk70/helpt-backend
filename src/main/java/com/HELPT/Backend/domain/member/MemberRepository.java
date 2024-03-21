package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.domain.library.jpamodule.callbackclass.JpaModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    public Member checkMember(String tk)
    {
        return JpaModule.executeTransaction(em -> {
            List<Member> resultList = em.createQuery("SELECT m FROM Member m WHERE accesstoken=:tk", Member.class)
                    .setParameter("tk", tk)
                    .getResultList();
            return resultList.isEmpty() ? null : resultList.get(0);
        });
    }

    public Member add(Member member)
    {
        return JpaModule.executeTransaction(em -> {
            em.persist(member);
            return member;
        });
    }
}
