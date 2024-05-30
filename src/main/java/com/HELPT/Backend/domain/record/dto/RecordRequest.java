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

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDate recordDate;

    private float successRate;

    private String comment;

    private String snapshotFile;

    @Builder
    public RecordRequest(Long equipmentId, int count, int setNumber, int weight, LocalDate recordDate, float successRate,
                         LocalDateTime startTime,LocalDateTime endTime,String comment) {
        this.startTime=startTime;
        this.endTime=endTime;
        this.equipmentId = equipmentId;
        this.count = count;
        this.setNumber = setNumber;
        this.weight = weight;
        this.recordDate = recordDate;
        this.successRate = successRate;
        this.comment=comment;
    }

    public void setSnapshotFile(String snapshotFile) {
        this.snapshotFile = snapshotFile;
    }
}
