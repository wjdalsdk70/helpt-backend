package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.domain.membership.Membership;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import static com.HELPT.Backend.domain.member.QMember.member;
import static  com.HELPT.Backend.domain.membership.QMembership.membership;

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

    public Member findById(int userid)
    {
        return em.find(Member.class,userid);
    }
    public Member findByToken(String tk)
    {
        return queryFactory.selectFrom(member)
                .where(member.accessToken.eq(tk))
                .fetchOne();
    }

    public Member update(Member member)
    {
        Member findMember = em.find(Member.class,member.getUserId());
        BeanUtils.copyProperties(member,findMember);


        return findMember;
    }

    public boolean attendance(int userid)
    {
        Membership findMembership = queryFactory.selectFrom(membership)
                .where(membership.userId.eq(userid))
                .fetchOne();
        if(findMembership==null){
            return false;
        }

        int attendanceDate = findMembership.getAttendanceDate();
        findMembership.setAttendanceDate(attendanceDate+1);
        return true;
    }

    public Member add(Member member)
    {
        em.persist(member);
        return member;
    }
}
