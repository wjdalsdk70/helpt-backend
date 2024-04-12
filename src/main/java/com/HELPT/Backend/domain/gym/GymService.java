package com.HELPT.Backend.domain.gym;

import com.HELPT.Backend.domain.gym.dto.GymResistrationRequest;
import com.HELPT.Backend.domain.gym.dto.GymResponse;
import com.HELPT.Backend.domain.gym.dto.GymRequest;
import com.HELPT.Backend.domain.gym.entity.Gym;
import com.HELPT.Backend.domain.gym.entity.GymRegistration;
import com.HELPT.Backend.domain.gym.entity.Status;
import com.HELPT.Backend.domain.gym.repository.GymRegistrationRepository;
import com.HELPT.Backend.domain.gym.repository.GymRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GymService {

    private final GymRepository gymRepository;
    private final GymRegistrationRepository gymRegistrationRepository;

    @Transactional
    public GymResponse addGym(GymResistrationRequest gymResistrationRequest) {
        Gym gym = gymResistrationRequest.toGymEntity();
        GymRegistration gymRegistrationEntity = gymResistrationRequest.toGymRegistrationEntity();
        gym.builder().gymRegistration(gymRegistrationEntity);
        gymRepository.save(gym);
        return GymResponse.builder().gym(gym).build();
    }

    @Transactional(readOnly = true)
    public GymResponse findGym(Long id) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gym not found"));
        return GymResponse.builder().gym(gym).build();
    }

    @Transactional(readOnly = true)
    public List<GymResponse> findGyms() {
        List<Gym> gyms = gymRepository.findAll();
        return gyms.stream()
                .map(gym -> new GymResponse(gym)) // GymResponse의 생성자 또는 빌더를 사용
                .collect(Collectors.toList());
    }

    @Transactional
    public GymResponse modifyGym(Long id, GymRequest gymRequest) {
        Gym gym = gymRepository.findById(id).orElseThrow(() -> new RuntimeException("Gym not found"));
        gym.updateAddress(gymRequest.getAddress());
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
