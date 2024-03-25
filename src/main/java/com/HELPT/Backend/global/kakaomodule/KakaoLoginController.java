package com.HELPT.Backend.global.kakaomodule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Slf4j
@RequiredArgsConstructor
public class KakaoLoginController {

    private final KakaoAPI kakaoAPI;

    //TODO:: 현재 테스트 위해 서버 local URL로 리디렉션, 실제로는 앱내에서 리디렉션후 응답 code만 서버로 전송 후 토큰을 클라이언트로 전송.
    @GetMapping("")
    public String login(@RequestParam("accessToken") String accessToken) {
        log.info("accessToken is {}", accessToken);
        KakaoUserInfo kakaoUserInfo = kakaoAPI.getUserInfo(accessToken);

        return String.format("%s님 안녕하세요!",kakaoUserInfo.getNickname());
    }
}
