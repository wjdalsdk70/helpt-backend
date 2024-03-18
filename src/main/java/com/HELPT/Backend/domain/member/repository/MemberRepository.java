package com.HELPT.Backend.domain.member.repository;

import com.HELPT.Backend.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @Autowired
    private static EntityManagerFactory emf;


    private boolean isToken(String tk)
    {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        boolean resultValue = false;
        try {


            tx.begin(); //트랜잭션 시작
             List<Member> resultList =  em.createQuery("SELECT m FROM Member m WHERE token=:tk", Member.class).getResultList();
            if(resultList.isEmpty()) resultValue = false;
            else resultValue=true;

            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
        return resultValue;



    }

}
