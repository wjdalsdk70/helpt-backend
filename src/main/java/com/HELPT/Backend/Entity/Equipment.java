package com.HELPT.Backend.Entity;

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
    private int equipmentId;

    private int gymId;

    private int defaultCount;

    private int defaultSet;

    private float defaultWeight;

    private String equipmentName;
}
