package com.HELPT.Backend.domain.gym;

import com.HELPT.Backend.domain.gym.dto.GymRegistrationDto;
import com.HELPT.Backend.domain.gym.dto.GymResistrationRequest;
import com.HELPT.Backend.domain.gym.dto.GymResponse;
import com.HELPT.Backend.domain.gym.dto.GymRequest;
import com.HELPT.Backend.domain.gym.entity.Gym;
import com.HELPT.Backend.domain.gym.entity.GymRegistration;
import com.HELPT.Backend.domain.gym.entity.Status;
import com.HELPT.Backend.domain.gym.repository.GymRepository;
import com.HELPT.Backend.domain.manager.Manager;
import com.HELPT.Backend.domain.manager.ManagerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.HELPT.Backend.global.auth.SecurityUtil.getCurrentUserId;

@Service
@RequiredArgsConstructor
public class GymService {

    private final GymRepository gymRepository;
    private final ManagerRepository managerRepository;

    @Transactional
    public GymResponse addGym(GymResistrationRequest gymInfo) {
        Gym gym = gymInfo.toGymEntity();
        GymRegistration gymRegistrationEntity = gymInfo.toGymRegistrationEntity();
        gym.updateGymRegistration(gymRegistrationEntity);
        gymRepository.save(gym);
        Optional<Manager> manager = managerRepository.findById(getCurrentUserId());
        manager.get().updateGym(gym);
        return GymResponse.builder().gym(gym).build();
    }

    @Transactional(readOnly = true)
    public GymResponse findGym(Long id) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gym not found"));
        return GymResponse.builder().gym(gym).build();
    }
    @Transactional(readOnly = true)
    public GymRegistrationDto findGymRegistration(Long id) {
        Gym gym = gymRepository.findById(id).orElseThrow(() -> new RuntimeException("Gym not found"));
        return GymRegistrationDto.toDto(gym.getGymRegistration());
    }


    @Transactional(readOnly = true)
    public List<GymResponse> findGyms() {
        List<Gym> gyms = gymRepository.findAll();
        return gyms.stream()
                .map(gym -> new GymResponse(gym))
                .collect(Collectors.toList());
    }

    @Transactional
    public GymResponse modifyGym(Long id, GymRequest gymRequest) {
        Gym gym = gymRepository.findById(id).orElseThrow(() -> new RuntimeException("Gym not found"));
//        gym.updateAddress(gymRequest.getAddress());
        gym.updateGymName(gymRequest.getGymName());
//        gym = gymRepository.save(gym); // 변경 감지
        return GymResponse.builder().gym(gym).build();
    }

    @Transactional
    public void removeGym(Long id) {
        Gym gym = gymRepository.findById(id).orElseThrow(() -> new RuntimeException("Gym not found"));
        gymRepository.delete(gym);
    }

    public List<Gym> findGymsByStatus(Status status) {
        return gymRepository.findByStatus(status);
    }

    public void updateGymStatus(Long gymId, Status status) {
        Gym gym = gymRepository.findById(gymId)
                .orElseThrow(() -> new EntityNotFoundException("Gym not found with id " + gymId));
        gym.updateStatus(status);
        gymRepository.save(gym);
    }
}
