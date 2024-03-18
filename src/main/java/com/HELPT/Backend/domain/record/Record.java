package com.HELPT.Backend.domain.record;

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
    private int rid;

    private int userid;

    private int equipmentid;

    private int count;

    private int set;

    private int weight;

    private Date startdate;

    private Date enddate;

    private float successrate;
}
