package com.HELPT.Backend.domain.gymequipment;

import com.HELPT.Backend.domain.equipment.Equipment;
import com.HELPT.Backend.domain.equipment.EquipmentRepository;
import com.HELPT.Backend.domain.gymequipment.dto.GymEquipmentRequest;
import com.HELPT.Backend.domain.gymequipment.dto.GymEquipmentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GymEquipmentServiceTest {

//    private EquipmentRepository equipmentRepository;
//    private GymEquipmentService gymEquipmentService;
//
//    private Equipment equipment;
//
//    @BeforeEach
//    public void setup() {
//        equipmentRepository.deleteAll();
//
//        Equipment defaultEquipment = new Equipment();
//        defaultEquipment.setEquipmentName("Treadmill");
//        defaultEquipment.setDefaultCount(10);
//        defaultEquipment.setDefaultSet(3);
//        defaultEquipment.setDefaultWeight(150);
//
//        equipmentRepository.save(defaultEquipment);
//    }
//
//
//    @Test
//    public void testAddGymEquipment() {
//        GymEquipmentRequest request = new GymEquipmentRequest(/* set properties */);
//        GymEquipment gymEquipment = request.toEntity();
//        GymEquipment savedEquipment = new GymEquipment(/* set properties */);
//
//        when(gymEquipmentRepository.save(any(GymEquipment.class))).thenReturn(savedEquipment);
//
//        GymEquipmentResponse result = gymEquipmentService.addGymEquipment(request);
//
//        assertNotNull(result);
//        // Assert various properties of the result based on the savedEquipment
//        assertEquals(savedEquipment.getId(), result.getId());
//        // more assertions
//    }

}