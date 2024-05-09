package com.HELPT.Backend.domain.record.dto;

import com.HELPT.Backend.domain.record.Record;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordResponse {

    private Long equipmentId;

    private int count;

    private int setNumber;

    private int weight;

    private LocalDateTime recordDate;

    private float successRate;


    public RecordResponse(Record record) {
        this.equipmentId = record.getEquipmentId();
        this.count = record.getCount();
        this.setNumber = record.getSetNumber();
        this.weight = record.getWeight();
        this.recordDate = record.getRecordDate();
        this.successRate = record.getSuccessRate();
    }
}
