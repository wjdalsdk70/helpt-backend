package com.HELPT.Backend.domain.exercise.dto;

import com.HELPT.Backend.domain.exercise.Exercise;
import com.HELPT.Backend.domain.product.Product;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseResponseDto {

    private String exerciseDescription;

    private List<String> exerciseMethod;

    private String topImage;

    private String bottomImage;

    public ExerciseResponseDto(Exercise exercise) {
        this.exerciseDescription = exercise.getExerciseDescription();

        this.topImage = exercise.getTopImage();
        this.bottomImage = exercise.getBottomImage();
    }
}
