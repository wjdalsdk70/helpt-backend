package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.global.auth.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.User;



@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private Long gymId;

    private String kakaoId;

    private String userName;

    private String gender;

    private float height;

    private float weight;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.MEMBER;


    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
