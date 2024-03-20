package com.HELPT.Backend.domain.kakao;

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
    @GetMapping("")
    public String login(@RequestParam("code") String code) {
        log.info("Authorization Code is {}", code);

        String accessToken = kakaoAPI.getAccessToken(code);
        log.info(accessToken);

        log.info("accessToken is {}", accessToken);
        KakaoUserInfo kakaoUserInfo = kakaoAPI.getUserInfo(accessToken);

        return String.format("%s님 안녕하세요!",kakaoUserInfo.getNickname());
    }
}
