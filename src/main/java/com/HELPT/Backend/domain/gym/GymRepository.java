package com.HELPT.Backend.domain.gym;

import com.HELPT.Backend.domain.gym.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym,Long> {
}
