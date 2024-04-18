package com.HELPT.Backend.domain.equipment;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EquipmentDto {

    private Long equipmentId;

    private String equipmentName;

    private int defaultCount;

    private int defaultSet;

    private int defaultWeight;

    public Equipment toEntity(){
        return Equipment.builder()
                .equipmentName(equipmentName)
                .defaultCount(defaultCount)
                .defaultSet(defaultSet)
                .defaultWeight(defaultWeight)
                .build();
    }

    public static EquipmentDto toDto(Equipment equipment){
        return EquipmentDto.builder()
                .equipmentId(equipment.getEquipmentId())
                .equipmentName(equipment.getEquipmentName())
                .defaultCount(equipment.getDefaultCount())
                .defaultSet(equipment.getDefaultSet())
                .defaultWeight(equipment.getDefaultWeight())
                .build();
    }

}
