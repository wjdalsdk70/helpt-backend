package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManagerFactory emf;

    public Member isToken(String tk)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        List<Member> resultList;
        try {
            tx.begin(); //트랜잭션 시작

            Query query =  em.createQuery("SELECT m FROM Member m WHERE token=:tk", Member.class);
            query.setParameter("tk",tk);
            resultList = query.getResultList();
            tx.commit();//트랜잭션 커밋
            if(!resultList.isEmpty())
            {
                return resultList.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        return null;
    }

    public Member add(Member member)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin(); //트랜잭션 시작
            em.persist(member);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        return member;
    }


}
