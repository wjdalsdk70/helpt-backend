package com.HELPT.Backend.domain.gym.dto;

import com.HELPT.Backend.domain.gym.Gym;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GymResponse {

    private Long gymId;
    private String address;
    private String gymName;

    @Builder
    public GymResponse(Gym gym) {
        this.gymId = gym.getGymId();
        this.address = gym.getAddress();
        this.gymName = gym.getGymName();
    }
}
