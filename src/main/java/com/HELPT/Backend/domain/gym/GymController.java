package com.HELPT.Backend.domain.gym;

import com.HELPT.Backend.domain.gym.dto.GymResponse;
import com.HELPT.Backend.domain.gym.dto.GymRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gyms")
public class GymController {

    private final GymService gymService;

    @PostMapping
    public ResponseEntity<GymResponse> createGym(@RequestBody GymRequest gymRequest){
        return ResponseEntity.ok(gymService.createGym(gymRequest));
    }

    @GetMapping("/{gym_id}")
    public ResponseEntity<GymResponse> getGym(@PathVariable Long id) {
        return ResponseEntity.ok(gymService.getGymById(id));
    }

    @GetMapping
    public ResponseEntity<List<GymResponse>> getAllGyms() {
        return ResponseEntity.ok(gymService.getAllGyms());
    }

    @PutMapping("/{gym_id}")
    public ResponseEntity<GymResponse> updateGym(@PathVariable Long id, @RequestBody GymRequest gymRequest) {
        return ResponseEntity.ok(gymService.updateGym(id, gymRequest));
    }

    @DeleteMapping("/{gym_id}")
    public ResponseEntity<Void> deleteGym(@PathVariable Long id) {
        gymService.deleteGym(id);
        return ResponseEntity.ok().build();
    }

}
