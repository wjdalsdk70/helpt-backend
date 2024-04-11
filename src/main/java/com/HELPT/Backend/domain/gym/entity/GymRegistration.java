package com.HELPT.Backend.domain.gym.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class GymRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "gym_id", referencedColumnName = "gymId")
    private Gym gym;

    private String contactNumber; // 연락처
    private String businessNumber; // 사업자 등록번호
    private String businessType; // 사업자 구분
    private String OwnerName; // 사업자 이름
    private String businessFile; // 사업자 등록증 파일 경로
}