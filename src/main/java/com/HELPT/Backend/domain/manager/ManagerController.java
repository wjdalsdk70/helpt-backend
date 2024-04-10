package com.HELPT.Backend.domain.manager;

import com.HELPT.Backend.domain.gym.dto.GymRequest;
import com.HELPT.Backend.domain.gym.dto.GymResponse;
import com.HELPT.Backend.domain.manager.dto.ManagerRequest;
import com.HELPT.Backend.domain.manager.dto.ManagerResponse;
import com.HELPT.Backend.domain.member.Dto.MemberDto;
import com.HELPT.Backend.global.auth.jwt.JWTResponse;
import com.HELPT.Backend.global.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/manager")
@Slf4j
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody ManagerRequest managerRequest) {
        return ResponseEntity.ok(managerService.login(managerRequest));
    }

    @GetMapping("/{gym_id}")
    public ResponseEntity<ManagerResponse> managerDetails(@PathVariable Long id) {
        return ResponseEntity.ok(managerService.findManager(id));
    }

    @GetMapping
    public ResponseEntity<List<ManagerResponse>> managerList() {
        return ResponseEntity.ok(managerService.findManagers());
    }

    @PutMapping("/{gym_id}")
    public ResponseEntity<ManagerResponse> managerModify(@PathVariable Long id, @RequestBody ManagerRequest managerRequest) {
        return ResponseEntity.ok(managerService.modifyManager(id, managerRequest));
    }

    @DeleteMapping("/{gym_id}")
    public ResponseEntity<Void> managerRemove(@PathVariable Long id) {
        managerService.removeManager(id);
        return ResponseEntity.ok().build();
    }
}
