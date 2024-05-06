package com.HELPT.Backend.domain.gymequipment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GymEquipmentRepository extends JpaRepository<GymEquipment,Long> {

    List<GymEquipment> findByGym_Id(Long gymId);
}
