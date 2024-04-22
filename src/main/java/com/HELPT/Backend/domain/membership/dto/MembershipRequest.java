package com.HELPT.Backend.domain.membership.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembershipRequest {

    private Long membershipId;

    @Builder
    public MembershipRequest(Long membershipId) {

        this.membershipId = membershipId;
    }
}
