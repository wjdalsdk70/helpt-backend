package com.HELPT.Backend.domain.manager;

import com.HELPT.Backend.global.auth.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerId;

    private Long gymId;

    private String kakaoId;
//    private String name;
//    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

}
