package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.domain.callbackclass.CallbackClass;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    public Member checkMember(String tk)
    {
        return CallbackClass.executeTransaction(em -> {
            Query query = em.createQuery("SELECT m FROM Member m WHERE token=:tk", Member.class);
            query.setParameter("tk", tk);
            List<Member> resultList = query.getResultList();
            return resultList.isEmpty() ? null : resultList.get(0);
        });
    }

    public Member add(Member member)
    {
        return CallbackClass.executeTransaction(em -> {
            em.persist(member);
            return member;
        });
    }
}
