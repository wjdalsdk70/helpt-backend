package com.HELPT.Backend.domain.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private int gymId;

    private String accessToken;

    private String refreshToken;

    private String userName;

    private String gender;

    private float height;

    private float weight;
}
