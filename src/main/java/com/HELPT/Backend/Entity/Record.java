package com.HELPT.Backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordId;

    private int userId;

    private int equipmentId;

    private int count;

    private int set;

    private int weight;

    private Date startDate;

    private Date endDate;

    private float successRate;
}
