package com.HELPT.Backend.domain.gym;

import com.HELPT.Backend.domain.gym.dto.GymResponse;
import com.HELPT.Backend.domain.gym.dto.GymRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GymService {

    private final GymRepository gymRepository;

    @Transactional
    public GymResponse createGym(GymRequest gymRequest) {
        Gym gym = gymRequest.toEntity();
        gymRepository.save(gym);
        return GymResponse.builder().gym(gym).build();
    }

    @Transactional(readOnly = true)
    public GymResponse getGymById(Long id) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gym not found"));
        return GymResponse.builder().gym(gym).build();
    }

    @Transactional(readOnly = true)
    public List<GymResponse> getAllGyms() {
        List<Gym> gyms = gymRepository.findAll();
        return gyms.stream()
                .map(gym -> new GymResponse(gym)) // GymResponse의 생성자 또는 빌더를 사용
                .collect(Collectors.toList());
    }

    @Transactional
    public GymResponse updateGym(Long id, GymRequest gymRequest) {
        Gym gym = gymRepository.findById(id).orElseThrow(() -> new RuntimeException("Gym not found"));
        gym.updateAddress(gymRequest.getAddress());
        gym.updateGymName(gymRequest.getGymName());
//        gym = gymRepository.save(gym); // 변경 감지
        return GymResponse.builder().gym(gym).build();
    }

    @Transactional
    public void deleteGym(Long id) {
        Gym gym = gymRepository.findById(id).orElseThrow(() -> new RuntimeException("Gym not found"));
        gymRepository.delete(gym);
    }

}
