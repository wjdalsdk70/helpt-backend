package com.HELPT.Backend.domain.modules.jpamodule.callbackclass;

import com.HELPT.Backend.domain.modules.jpamodule.callbackinterface.TransactionCallback;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaModule {

    private static EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        JpaModule.emf = emf;
    }

    public static  <T> T executeTransaction(TransactionCallback<T> callback) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            T result = callback.action(em);

            tx.commit();
            return result;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback(); // 트랜잭션 롤백
            }
            e.printStackTrace();
            throw new RuntimeException("Transaction failed: " + e.getMessage());
        } finally {
            em.close(); // 엔티티 매니저 종료
        }
    }
}
