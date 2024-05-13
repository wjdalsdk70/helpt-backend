package com.HELPT.Backend.domain.equipment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EquipmentServiceTest {

    @InjectMocks
    EquipmentService equipmentService;

    @Mock
    EquipmentRepository equipmentRepository;

    @Test
    @DisplayName("AI 기구 생성 서비스 테스트")
    void saveEquipmentServiceTest(){
        // given
        Equipment equipment = Equipment.builder()
                .equipmentName("벤치프레스")
                .defaultCount(10)
                .defaultSet(5)
                .defaultWeight(25)
                .build();
        EquipmentDto equipmentDto = EquipmentDto.builder()
                .equipmentName("벤치프레스")
                .defaultCount(10)
                .defaultSet(5)
                .defaultWeight(25)
                .build();
        given(equipmentRepository.save(any(Equipment.class))).willReturn(equipment);

        // when
        EquipmentDto result = equipmentService.addEquipment(equipmentDto);

        // then
        verify(equipmentRepository).save(any(Equipment.class));
        assertEquals(result.getEquipmentName(),equipment.getEquipmentName());
    }

    @Test
    @DisplayName("AI 기구 단건 조회 서비스 테스트")
    void findEquipmentServiceTest(){
        // given
        Equipment equipment = Equipment.builder()
                .equipmentName("벤치프레스")
                .defaultCount(10)
                .defaultSet(5)
                .defaultWeight(25)
                .build();
        given(equipmentRepository.findById(anyLong())).willReturn(Optional.of(equipment));

        // when
        EquipmentDto result = equipmentService.findEquipment(1L);

        // then
        verify(equipmentRepository).findById(anyLong());
        assertThat(result.getEquipmentName()).isEqualTo(equipment.getEquipmentName());
    }
}