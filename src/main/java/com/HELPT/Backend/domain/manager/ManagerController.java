package com.HELPT.Backend.domain.manager;

import com.HELPT.Backend.domain.gym.dto.GymRequest;
import com.HELPT.Backend.domain.gym.dto.GymResponse;
import com.HELPT.Backend.domain.manager.dto.KakaoLoginRequest;
import com.HELPT.Backend.domain.manager.dto.KakaoLoginResponse;
import com.HELPT.Backend.domain.manager.dto.ManagerRequest;
import com.HELPT.Backend.domain.manager.dto.ManagerResponse;
import com.HELPT.Backend.domain.manager.dto.MemberJoinResponse;
import com.HELPT.Backend.domain.member.Dto.MemberDto;
import com.HELPT.Backend.global.auth.jwt.JWTResponse;
import com.HELPT.Backend.global.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.HELPT.Backend.global.auth.SecurityUtil.getCurrentUserId;

@RequiredArgsConstructor
@RestController
@RequestMapping("/managers")
@Slf4j
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/login")
    public ResponseEntity<KakaoLoginResponse> loginTest(@RequestBody KakaoLoginRequest kakaoLoginRequest) {
        return ResponseEntity.ok(managerService.login(kakaoLoginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<JWTResponse> register(@RequestBody ManagerRequest managerRequest) {
        return ResponseEntity.ok(managerService.register(managerRequest));
    }


//    @GetMapping("/{gym_id}")
//    public ResponseEntity<ManagerResponse> managerDetails(@PathVariable Long id) {
//        return ResponseEntity.ok(managerService.findManager(id));
//    }

    @GetMapping
    public ResponseEntity<List<ManagerResponse>> managerList() {
        return ResponseEntity.ok(managerService.findManagers());
    }

//    @PutMapping("/{gym_id}")
//    public ResponseEntity<ManagerResponse> managerModify(@PathVariable Long id, @RequestBody ManagerRequest managerRequest) {
//        return ResponseEntity.ok(managerService.modifyManager(id, managerRequest));
//    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberJoinResponse>> memberList() {
        Long userId = getCurrentUserId();
        ManagerResponse mr = managerService.findManager(userId);
        Long gymId = mr.getGymId();

        return ResponseEntity.ok(managerService.memberList(gymId));
    }

//    @PutMapping("/{gym_id}")
//    public ResponseEntity<ManagerResponse> managerModify(@PathVariable Long id, @RequestBody ManagerRequest managerRequest) {
//        return ResponseEntity.ok(managerService.modifyManager(id, managerRequest));
//    }

    @DeleteMapping
    public ResponseEntity<Void> memberRemove() {
        Long userId = getCurrentUserId();
        managerService.removeManager(userId);
        return ResponseEntity.ok().build();
    }
}
