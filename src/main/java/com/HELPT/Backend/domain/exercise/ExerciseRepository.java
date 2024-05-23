package com.HELPT.Backend.domain.exercise;

import com.HELPT.Backend.domain.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {
}
