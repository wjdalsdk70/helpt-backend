package com.HELPT.Backend.domain.gym.entity;

import jakarta.persistence.*;
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
    private String chat_link;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gym_registration_id")
    private GymRegistration gymRegistration;

    @Builder.Default
    private Status status = Status.Pending;

    public void updateAddress(String address) {
        this.address = address;
    }

    public void updateGymName(String gymName) {
        this.gymName = gymName;
    }

    public void updateStatus(Status status) {this.status = status;
    }
}
