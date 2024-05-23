package com.HELPT.Backend.domain.exercise;

import com.HELPT.Backend.domain.equipment.Equipment;
import com.HELPT.Backend.domain.equipment.EquipmentDto;
import com.HELPT.Backend.domain.exercise.dto.ExerciseRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public Exercise uploadExercise(ExerciseRequestDto exerciseRequestDto, String topImage, String bottomImage) {
        Exercise exercise = Exercise.builder()
                .exerciseDescription(exerciseRequestDto.getExerciseDescription())
                .exerciseMethod(exerciseRequestDto.getExerciseMethod())
                .topImage(topImage)
                .bottomImage(bottomImage)
                .build();


        return exerciseRepository.save(exercise);
    }
}
