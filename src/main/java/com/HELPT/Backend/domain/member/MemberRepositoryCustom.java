package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.domain.member.Dto.MemberDetailResponse;
import com.HELPT.Backend.domain.member.Dto.MemberJoinResponse;
import com.HELPT.Backend.domain.membership.Membership;

import java.util.List;

public interface MemberRepositoryCustom {
    public Membership attendance(Long userid);
    public MemberDetailResponse memberDetail(Long memberId);
    public List<MemberJoinResponse> memberList(Long gymId, String name);

}
