package com.HELPT.Backend.domain.gymadmission;

import com.HELPT.Backend.global.error.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.HELPT.Backend.global.auth.SecurityUtil.getCurrentUserId;
import static com.HELPT.Backend.global.error.ErrorCode.EXIST_REQUEST;

@RestController
@RequestMapping("/gyms/{gymId}/gym-admissions")
@RequiredArgsConstructor
@Slf4j
public class GymAdmissionController {

    private final GymAdmissionService gymAdmissionService;

    @GetMapping
    public ResponseEntity<List<GymAdmissionResponse>> gymAdmissionList(@PathVariable Long gymId) {
        return ResponseEntity.ok(gymAdmissionService.findGymAdmissions(gymId));
    }

    @PostMapping
    public ResponseEntity<GymAdmission> gymAdmissionAdd(@PathVariable Long gymId) {
        Long userId = getCurrentUserId();
        GymAdmission gymAdmission = gymAdmissionService.addGymAdmission(gymId, userId);
        if(gymAdmission == null){
            throw new CustomException(EXIST_REQUEST);
        }
        return ResponseEntity.ok(gymAdmission);
    }

    @DeleteMapping("/{gymAdmissionId}/reject")
    public ResponseEntity<Void> gymAdmissionReject(@PathVariable Long gymAdmissionId){
        gymAdmissionService.rejectGymAdmission(gymAdmissionId);
        return ResponseEntity.ok().build();
    }
}
