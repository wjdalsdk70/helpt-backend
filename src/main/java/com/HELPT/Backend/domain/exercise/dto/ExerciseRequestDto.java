package com.HELPT.Backend.domain.exercise.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseRequestDto {

    private String exerciseDescription;

    private String exerciseMethod;
}
