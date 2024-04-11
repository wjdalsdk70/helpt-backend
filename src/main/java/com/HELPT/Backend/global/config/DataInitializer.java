package com.HELPT.Backend.global.config;

import com.HELPT.Backend.domain.admin.Admin;
import com.HELPT.Backend.domain.admin.AdminRepository;
import com.HELPT.Backend.domain.gym.GymRepository;
import com.HELPT.Backend.domain.gym.entity.Gym;
import com.HELPT.Backend.domain.gym.entity.Status;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.HELPT.Backend.global.auth.Role.ADMIN;

@Component
@RequiredArgsConstructor
public class DataInitializer{

    private final AdminRepository adminRepository;
    private final GymRepository gymRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        // 관리자 계정이 존재하지 않으면 생성
        if (adminRepository.findByLoginId("wjdalsdk70").isEmpty()) {
            String encodedPassword = passwordEncoder.encode("1234");
            Admin admin = Admin.builder().loginId("wjdalsdk70").password(encodedPassword).role(ADMIN).build();
            adminRepository.save(admin);
        }
        Gym gym = Gym.builder().gymName("나이스짐")
                .status(Status.Pending)
                .build();
        gymRepository.save(gym);
    }
}