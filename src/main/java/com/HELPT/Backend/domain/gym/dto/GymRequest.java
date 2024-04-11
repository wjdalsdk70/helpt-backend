package com.HELPT.Backend.domain.gym.dto;

import com.HELPT.Backend.domain.gym.entity.Gym;
import com.HELPT.Backend.domain.gym.entity.Status;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GymRequest {

    private String address;
    private String gymName;
    private Status status;

    public Gym toEntity(){
        return Gym.builder()
                .address(address)
                .gymName(gymName)
                .status(status)
                .build();
    }
}
