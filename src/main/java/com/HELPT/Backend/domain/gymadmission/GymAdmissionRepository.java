package com.HELPT.Backend.domain.gymadmission;

import com.HELPT.Backend.domain.gym.entity.Gym;
import com.HELPT.Backend.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GymAdmissionRepository extends JpaRepository<GymAdmission,Long> {
    Optional<GymAdmission> findByGymAndMember(Gym gym, Member member);
}
