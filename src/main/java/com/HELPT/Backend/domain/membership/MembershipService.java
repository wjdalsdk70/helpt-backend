package com.HELPT.Backend.domain.membership;

import com.HELPT.Backend.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public Membership findMembership(Long userid)
    {
        return membershipRepository.findByUserId(userid).orElseThrow(() -> new RuntimeException("Membership not found"));
    }
    public Membership addMembership(Membership membership)
    {
        Membership newMembership = membershipRepository.save(membership);
        return newMembership;
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
