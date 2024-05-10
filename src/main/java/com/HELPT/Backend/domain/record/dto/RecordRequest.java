package com.HELPT.Backend.domain.record.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordRequest {

    private Long equipmentId;

    private int count;

    private int setNumber;

    private int weight;

    private LocalDate recordDate;

    private float successRate;

    @Builder
    public RecordRequest(Long equipmentId, int count, int setNumber, int weight, LocalDate recordDate, float successRate) {
        this.equipmentId = equipmentId;
        this.count = count;
        this.setNumber = setNumber;
        this.weight = weight;
        this.recordDate = recordDate;
        this.successRate = successRate;
    }
}
