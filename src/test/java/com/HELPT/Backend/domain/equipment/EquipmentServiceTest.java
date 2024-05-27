package com.HELPT.Backend.domain.equipment;

import com.HELPT.Backend.domain.equipment.dto.EquipmentDto;
import org.junit.jupiter.api.BeforeEach;
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

    private Equipment equipment;

    @BeforeEach
    void setUp() {
        equipment = Equipment.builder()
                .equipmentId(1L)
                .exerciseId(100L)
                .equipmentName("벤치 프레스")
                .defaultCount(10)
                .defaultSet(5)
                .defaultWeight(25)
                .build();
    }
    @Test
    @DisplayName("[Service] 기구 생성 서비스 테스트")
    void saveEquipmentServiceTest(){
        // given
        EquipmentDto equipmentDto = EquipmentDto.builder()
                .equipmentName("벤치프레스")
                .defaultCount(10)
                .defaultSet(5)
                .defaultWeight(25)
                .build();
        given(equipmentRepository.save(any(Equipment.class))).willReturn(equipment);

        // when
        EquipmentDto savedEquipmentDto = equipmentService.addEquipment(equipmentDto);

        // then
        verify(equipmentRepository).save(any(Equipment.class));
        assertEquals(savedEquipmentDto.getEquipmentName(),equipment.getEquipmentName());
    }

    @Test
    @DisplayName("[Service] 기구 단건 조회 서비스 테스트")
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

    @Test
    @DisplayName("[Service] 기구 리스트 조회 서비스 테스트")
    void findEquipmentListServiceTest(){
        // given
        List<Equipment> equipments = new ArrayList<>();
        Equipment equipment1 = Equipment.builder()
                .equipmentName("벤치프레스")
                .defaultCount(10)
                .defaultSet(5)
                .defaultWeight(25)
                .build();
        Equipment equipment2 = Equipment.builder()
                .equipmentName("스쿼트")
                .defaultCount(10)
                .defaultSet(5)
                .defaultWeight(25)
                .build();
        equipments.add(equipment1);
        equipments.add(equipment2);
        given(equipmentRepository.findAll()).willReturn(equipments);

        // when
        List<EquipmentDto> result = equipmentService.findEquipments();

        // then
        verify(equipmentRepository).findAll();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("[Service] 기구 수정 서비스 테스트")
    void modifyEquipmenServiceTest(){
        // given
        Equipment equipment = Equipment.builder()
                .equipmentName("벤치프레스")
                .defaultCount(10)
                .defaultSet(5)
                .defaultWeight(25)
                .build();
        EquipmentDto equipmentDto = EquipmentDto.builder()
                .equipmentName("스쿼트")
                .defaultCount(10)
                .defaultSet(5)
                .defaultWeight(25)
                .build();
        given(equipmentRepository.findById(anyLong())).willReturn(Optional.of(equipment));

        // when
        EquipmentDto result = equipmentService.modifyEquipment(1L, equipmentDto);

        // then
        assertThat(result.getEquipmentName()).isEqualTo(equipmentDto.getEquipmentName());
    }

    @Test
    @DisplayName("[Service] 기구 삭제 서비스 테스트")
    void removeEquipmenServiceTest(){
        // given
        Equipment equipment = Equipment.builder()
                .equipmentName("벤치프레스")
                .defaultCount(10)
                .defaultSet(5)
                .defaultWeight(25)
                .build();
        given(equipmentRepository.findById(anyLong())).willReturn(Optional.of(equipment));

        // when
        equipmentService.removeEquipment(1L);

        // then
        verify(equipmentRepository).delete(any());
    }
}