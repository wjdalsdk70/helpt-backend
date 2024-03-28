package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.domain.member.Dto.MemberDto;
import com.HELPT.Backend.global.kakaomodule.KakaoAPI;
import com.HELPT.Backend.global.kakaomodule.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final KakaoAPI kakaoAPI;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("accessToken") String accessToken) {
        KakaoUserInfo kakaoUserInfo = kakaoAPI.getUserInfo(accessToken);
        String kakaoId = kakaoUserInfo.getId();

        return ResponseEntity.ok("true");
    }


}
