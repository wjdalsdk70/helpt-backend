package com.HELPT.Backend.domain.admin;

import com.HELPT.Backend.domain.admin.dto.AdminRequest;
import com.HELPT.Backend.domain.gym.GymService;
import com.HELPT.Backend.domain.gym.entity.Gym;
import com.HELPT.Backend.domain.gym.entity.Status;
import com.HELPT.Backend.domain.manager.dto.ManagerRequest;
import com.HELPT.Backend.global.auth.jwt.JWTResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final GymService gymService;

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute AdminRequest adminRequest, HttpServletResponse response) {
        JWTResponse jwtResponse = adminService.login(adminRequest);
        if (jwtResponse != null) {
            // 쿠키에 토큰 저장
            Cookie cookie = new Cookie("AUTH_TOKEN", jwtResponse.getAccessToken());
            cookie.setHttpOnly(true); // JavaScript를 통한 접근 방지
            cookie.setPath("/"); // 사이트 전역에서 쿠키 접근 가능
            response.addCookie(cookie);
            return "redirect:/admin/gyms/pending";
        } else {
            return "login";
        }
    }

    @GetMapping("/gyms/pending")
    public String listPendingGyms(Model model) {
        List<Gym> pendingGyms = gymService.findGymsByStatus(Status.Pending);
        model.addAttribute("pendingGyms", pendingGyms);
        return "gyms/pending";
    }

    @PostMapping("/gyms/updateStatus")
    public String updateGymStatus(@RequestParam Long gymId, @RequestParam Status status) {
        gymService.updateGymStatus(gymId, status);
        return "redirect:/admin/gyms/pending";
    }

}
