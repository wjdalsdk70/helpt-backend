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
    private int userid;

    private int gymid;

    private String accesstoken;

    private String refreshtoken;

    private String username;

    private String gender;

    private float height;

    private float weight;
}
