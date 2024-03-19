package com.HELPT.Backend.domain.equipment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int equipmentid;

    private int gymid;

    private int defaultcount;

    private int defaultset;

    private float defaultweight;

    private String equipmentname;
}
