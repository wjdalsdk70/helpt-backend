package com.HELPT.Backend.domain.gym;

import com.HELPT.Backend.domain.gym.dto.GymRegistrationDto;
import com.HELPT.Backend.domain.gym.dto.GymResistrationRequest;
import com.HELPT.Backend.domain.gym.dto.GymResponse;
import com.HELPT.Backend.domain.gym.dto.GymRequest;
import com.HELPT.Backend.domain.gym.entity.GymRegistration;
import com.HELPT.Backend.global.s3.S3Uploader;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.HELPT.Backend.global.auth.SecurityUtil.getCurrentUserId;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gyms")
@Slf4j
public class GymController {

    private final GymService gymService;
    private final S3Uploader s3Uploader;

    @PostMapping
    public ResponseEntity<GymResponse> gymAdd(@RequestBody GymResistrationRequest gymResistrationRequest){
        return ResponseEntity.ok(gymService.addGym(gymResistrationRequest));
    }

    @PostMapping(value = "/file", consumes = "multipart/form-data")
    public ResponseEntity<?> registerGym(
            @RequestPart("gymInfo") GymResistrationRequest gymInfo,
            @RequestPart("businessFile") MultipartFile businessFile) {
        String uploadURL;
        try {
            uploadURL = s3Uploader.upload(businessFile, "businessFile");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gymInfo.updateBusinessFile(uploadURL);
        return ResponseEntity.ok(gymService.addGym(gymInfo));
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
