package com.HELPT.Backend.domain.membership;

import com.HELPT.Backend.domain.member.Member;
import com.HELPT.Backend.domain.membership.dto.MembershipRequest;
import com.HELPT.Backend.domain.membership.dto.MembershipResponse;
import com.HELPT.Backend.domain.product.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class MembershipService {

    private final MembershipRepository membershipRepository;

    @Autowired
    private final EntityManager em;

    public Membership findMembership(Long userid)
    {
        return membershipRepository.findByUserId(userid).orElseThrow(() -> new RuntimeException("Membership not found"));
    }

    public void removeMembership(MembershipRequest membershipRequest)
    {
        membershipRepository.deleteById(membershipRequest.getMembershipId());
    }


    public MembershipResponse addMembership(Long userId, Long productId)
    {
        Product product = membershipRepository.findProduct(productId);
        Membership addMembership = Membership.builder()
                .userId(userId)
                .gymId(product.getGymId())
                .build();
        Membership saveMembership = membershipRepository.save(addMembership);

        em.flush();
        em.clear();

        membershipRepository.findById(saveMembership.getMembershipId());
        LocalDate startDate = saveMembership.getStartDate();
        LocalDate endDate = startDate.plusDays(product.getDay());
        saveMembership.setEndDate(endDate);

        return new MembershipResponse(saveMembership);
    }

    public Membership extensionMembership(Long userId,int addDays)
    {
        Membership findMembership = membershipRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Membership not found"));
        LocalDate endDate = findMembership.getEndDate();
        LocalDate extensionDate = endDate.plusDays(addDays);
        findMembership.setEndDate(extensionDate);

        return findMembership;
    }
}
