package com.HELPT.Backend.domain.manager;

import com.HELPT.Backend.domain.gym.dto.GymRequest;
import com.HELPT.Backend.domain.gym.dto.GymResponse;
import com.HELPT.Backend.domain.gym.entity.Gym;
import com.HELPT.Backend.domain.manager.dto.ManagerRequest;
import com.HELPT.Backend.domain.manager.dto.ManagerResponse;
import com.HELPT.Backend.domain.member.Dto.MemberDto;
import com.HELPT.Backend.domain.member.Member;
import com.HELPT.Backend.global.auth.jwt.JWTResponse;
import com.HELPT.Backend.global.auth.jwt.JWTToken;
import com.HELPT.Backend.global.auth.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.HELPT.Backend.domain.member.QMember.member;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final JWTUtil jwtUtil;

    public JWTResponse login(ManagerRequest managerRequest) {
        try {
            String kakaoId = managerRequest.getKakaoId();
            Optional<Manager> existManager = managerRepository.findByKakaoId(kakaoId);

            Manager manager;
            if (existManager.isPresent()) {
                manager = existManager.get();
            } else {
                managerRepository.save(managerRequest.toEntity());
                manager = managerRepository.findByKakaoId(kakaoId).orElseThrow(() -> new RuntimeException("Manager could not be retrieved after save."));
            }

            JWTToken jwt = jwtUtil.createTokens(manager.getManagerId());
            return JWTResponse.builder().token(jwt).build();
        } catch (Exception e) {
            log.info("Login error", e);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public ManagerResponse findManager(Long id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        return new ManagerResponse(manager);
    }

    @Transactional(readOnly = true)
    public List<ManagerResponse> findManagers() {
        List<Manager> managers = managerRepository.findAll();
        return managers.stream()
                .map(manager -> new ManagerResponse(manager)) // ManagerResponse 생성자 또는 빌더 사용
                .collect(Collectors.toList());
    }

    @Transactional
    public ManagerResponse modifyManager(Long id, ManagerRequest managerRequest) {
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new RuntimeException("Manager not found"));

        return ManagerResponse.builder().manager(manager).build();
    }

    @Transactional
    public void removeManager(Long id) {
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new RuntimeException("Manager not found"));
        managerRepository.delete(manager);
    }

    @Transactional(readOnly = true)
    public JWTResponse loginTest(String kakaoId) {
        Manager manager = managerRepository.findByKakaoId(kakaoId).orElseThrow(() -> new RuntimeException("Manager not found"));
        JWTToken jwt = jwtUtil.createTokens(manager.getManagerId());
        return JWTResponse.builder().token(jwt).build();
    }
}
