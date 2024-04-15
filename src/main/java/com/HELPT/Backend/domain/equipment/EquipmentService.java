package com.HELPT.Backend.domain.equipment;

import com.HELPT.Backend.domain.gym.dto.GymRegistrationDto;
import com.HELPT.Backend.domain.gym.dto.GymRequest;
import com.HELPT.Backend.domain.gym.dto.GymResistrationRequest;
import com.HELPT.Backend.domain.gym.dto.GymResponse;
import com.HELPT.Backend.domain.gym.entity.Gym;
import com.HELPT.Backend.domain.gym.entity.GymRegistration;
import com.HELPT.Backend.domain.gym.entity.Status;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private EquipmentRepository equipmentRepository;

    @Transactional
    public EquipmentDto addEquipment(EquipmentDto equipmentDto) {
        Equipment equipment = equipmentDto.toEntity();
        equipmentRepository.save(equipment);
        return EquipmentDto.toDto(equipment);
    }

    @Transactional(readOnly = true)
    public EquipmentDto findEquipment(Long id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));
        return EquipmentDto.toDto(equipment);
    }

    @Transactional(readOnly = true)
    public List<EquipmentDto> findEquipments() {
        List<Equipment> equipmentList = equipmentRepository.findAll();
        return equipmentList.stream()
                .map(equipment -> EquipmentDto.toDto(equipment))
                .collect(Collectors.toList());
    }

    @Transactional
    public EquipmentDto modifyEquipment(Long id, EquipmentDto equipmentDto) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Equipment not found"));
        equipment.updateEquipmentName(equipmentDto.getEquipmentName());
        equipment.updateDefaultCount(equipmentDto.getDefaultCount());
        equipment.updateDefaultSet(equipmentDto.getDefaultSet());
        equipment.updateDefaultWeight(equipmentDto.getDefaultWeight());
        return EquipmentDto.toDto(equipment);
    }

    @Transactional
    public void removeEquipment(Long id) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Gym not found"));
        equipmentRepository.delete(equipment);
    }
}
