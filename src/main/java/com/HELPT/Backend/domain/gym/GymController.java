package com.HELPT.Backend.domain.gym;

import com.HELPT.Backend.domain.gym.dto.GymRegistrationDto;
import com.HELPT.Backend.domain.gym.dto.GymResistrationRequest;
import com.HELPT.Backend.domain.gym.dto.GymResponse;
import com.HELPT.Backend.domain.gym.dto.GymRequest;
import com.HELPT.Backend.domain.gym.entity.GymRegistration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.HELPT.Backend.global.auth.SecurityUtil.getCurrentUserId;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gyms")
@Slf4j
public class GymController {

    private final GymService gymService;

    @PostMapping
    public ResponseEntity<GymResponse> gymAdd(@RequestBody GymResistrationRequest gymResistrationRequest){
        return ResponseEntity.ok(gymService.addGym(gymResistrationRequest));
    }

    @GetMapping("/{gym_id}")
    public ResponseEntity<GymResponse> gymDetails(@PathVariable Long gym_id) {
        return ResponseEntity.ok(gymService.findGym(gym_id));
    }

    @GetMapping("/{gym_id}/gymregistrations")
    public ResponseEntity<GymRegistrationDto> gymRegistrationDetails(@PathVariable Long gym_id) {
        return ResponseEntity.ok(gymService.findGymRegistration(gym_id));
    }

    @GetMapping
    public ResponseEntity<List<GymResponse>> gymList() {
        return ResponseEntity.ok(gymService.findGyms());
    }

    @PutMapping("/{gym_id}")
    public ResponseEntity<GymResponse> gymModify(@PathVariable Long gym_id, @RequestBody GymRequest gymRequest) {
        return ResponseEntity.ok(gymService.modifyGym(gym_id, gymRequest));
    }

    @DeleteMapping("/{gym_id}")
    public ResponseEntity<Void> gymRemove(@PathVariable Long gym_id) {
        gymService.removeGym(gym_id);
        return ResponseEntity.ok().build();
    }
}
