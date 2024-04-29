package com.HELPT.Backend.domain.gym.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String fullAddress;
    private String detailAddress;
    private String latitude;
    private String longitude;
}