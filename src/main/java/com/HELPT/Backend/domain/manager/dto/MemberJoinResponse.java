package com.HELPT.Backend.domain.manager.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberJoinResponse {

    private Long userId;
    private String userName;
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public MemberJoinResponse(Long userId, String userName, LocalDate startDate, LocalDate endDate) {
        this.userId = userId;
        this.userName = userName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
