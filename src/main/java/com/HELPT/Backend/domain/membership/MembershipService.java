package com.HELPT.Backend.domain.membership;

import com.HELPT.Backend.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public Membership findMembership(int userid)
    {
        return membershipRepository.findByUserid(userid);
    }
    public Membership addMembership(Membership membership)
    {
        return membershipRepository.add(membership);
    }
}
