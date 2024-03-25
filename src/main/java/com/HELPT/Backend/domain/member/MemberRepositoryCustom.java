package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.domain.membership.Membership;

import java.util.List;

public interface MemberRepositoryCustom {
    public Membership attendance(Long userid);

}
