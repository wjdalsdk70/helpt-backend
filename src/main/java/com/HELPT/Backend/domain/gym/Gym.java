package com.HELPT.Backend.domain.gym;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gymId;

    private String address;

    private String gymName;

    public void updateAddress(String address) {
        this.address = address;
    }

    public void updateGymName(String gymName) {
        this.gymName = gymName;
    }
}
