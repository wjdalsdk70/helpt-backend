package com.HELPT.Backend.domain.record;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    private Long userId;

    private Long equipmentId;

    private int count;

    private int set;

    private int weight;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private float successRate;
}
