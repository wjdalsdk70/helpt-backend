package com.HELPT.Backend.domain.manager;

import com.HELPT.Backend.domain.manager.dto.MemberJoinResponse;
import com.HELPT.Backend.domain.member.Member;

import java.util.List;

public interface ManagerRepositoryCustom {
    public List<MemberJoinResponse> MemberList(Long gymId);
}
