package com.HELPT.Backend.domain.membership.dto;

import com.HELPT.Backend.domain.membership.Membership;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembershipResponse {

    private Long membershipId;
    private int attendanceDate;
    private LocalDate startDate;
    private LocalDate endDate;

    public MembershipResponse(Membership membership) {
        this.membershipId = membership.getMembershipId();
        this.attendanceDate = membership.getAttendanceDate();
        this.startDate = membership.getStartDate();
        this.endDate = membership.getEndDate();
    }
}
