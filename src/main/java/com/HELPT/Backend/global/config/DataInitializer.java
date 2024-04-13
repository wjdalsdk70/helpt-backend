package com.HELPT.Backend.global.config;

import com.HELPT.Backend.domain.admin.Admin;
import com.HELPT.Backend.domain.admin.AdminRepository;
import com.HELPT.Backend.domain.gym.entity.GymRegistration;
import com.HELPT.Backend.domain.gym.repository.GymRepository;
import com.HELPT.Backend.domain.gym.entity.Gym;
import com.HELPT.Backend.domain.gym.entity.Status;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
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
        GymRegistration gr = GymRegistration.builder()
                .contactNumber("010-1234-5678")
                .businessNumber("123-45-67890")
                .businessType("개인")
                .OwnerName("홍길동")
                .businessFile("/path/to/file.pdf")
                .build();

        Gym gym = Gym.builder().gymName("나이스짐")
                .address("경기도 수원시 팔달구 아주로 37 아록빌딩 7층 나이스짐")
                .gymRegistration(gr)
                .build();
        gymRepository.save(gym);


    }
}