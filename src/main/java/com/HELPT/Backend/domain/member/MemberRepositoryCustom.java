package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.domain.manager.dto.MemberJoinResponse;
import com.HELPT.Backend.domain.membership.Membership;

import java.util.List;

public interface MemberRepositoryCustom {
    public Membership attendance(Long userid);
    public List<MemberJoinResponse> memberList(Long gymId, String name);

}
