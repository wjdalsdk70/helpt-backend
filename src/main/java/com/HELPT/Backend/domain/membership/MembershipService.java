package com.HELPT.Backend.domain.membership;

import com.HELPT.Backend.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
}
