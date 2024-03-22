package com.HELPT.Backend.domain.membership;

import com.HELPT.Backend.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public Membership checkMembership(int userid)
    {
        return membershipRepository.findByUserid(userid);
    }
}
