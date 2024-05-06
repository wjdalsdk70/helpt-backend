package com.HELPT.Backend.domain.gymequipment;

import com.HELPT.Backend.domain.equipment.Equipment;
import com.HELPT.Backend.domain.gym.entity.Gym;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GymEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipmentId;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    private int customCount;

    private int customSet;

    private int customWeight;
}
