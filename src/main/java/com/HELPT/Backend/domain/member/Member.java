package com.HELPT.Backend.domain.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@Getter
@ToString
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private Long gymId;

    private Long kakaoId;

    private String userName;

    private String gender;

    private float height;

    private float weight;

    @Builder
    public Member(Long gymId, Long kakaoId, String userName, String gender, float height, float weight) {
        this.gymId = gymId;
        this.kakaoId = kakaoId;
        this.userName = userName;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
