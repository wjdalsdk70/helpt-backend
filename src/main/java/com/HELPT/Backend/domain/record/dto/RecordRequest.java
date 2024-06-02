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

    private String recordTime;

    private float successRate;

    private String comment;

    private String snapshotFile;

    @Builder
    public RecordRequest(Long equipmentId, int count, int setNumber, int weight, LocalDate recordDate, float successRate,
                         String recordTime ,String comment) {
        this.recordTime = recordTime;
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
