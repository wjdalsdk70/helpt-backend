package com.HELPT.Backend.domain.qr;

import com.HELPT.Backend.domain.membership.dto.MembershipResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.HELPT.Backend.global.auth.SecurityUtil.getCurrentUserId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qr")
public class QrCodeController {

    private final QrCodeService qrCodeService;

    @PostMapping
    public ResponseEntity<Map<String,String>> qrCodeAdd(){
        Long userId = getCurrentUserId();
        Map<String, String> map = new HashMap<>();
        map.put("qrToken",qrCodeService.addQrCode(userId));
        return ResponseEntity.ok(map);
    }

    @PostMapping("/verify/gyms/{gymId}")
    public ResponseEntity<MembershipResponse> qrCodeVerify(@PathVariable Long gymId){
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(qrCodeService.verifyQrCode(userId,gymId));
    }
}
