package com.HELPT.Backend.global.kakaomodule;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KakaoAPI {

    //private final String CLIENT_ID = "9fd7e81a8247e2989395288e7e20d318";
    private final String KAKAO_API_URL = "https://kauth.kakao.com/oauth/token";
    private final String KAKAO_VAILDATE_API_URL = "https://kapi.kakao.com/v1/user/access_token_info";
    private final String KAKAO_REFRESH_API_URL = "https://kauth.kakao.com/oauth/token";
    private final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
    private final String REDIRECT_URL = "http://localhost:8080/login";

    @Value("${kakao.appkey}")
    private String KAKAO_API_KEY;

    public String getAccessToken(String authorizationCode) {

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", KAKAO_API_KEY);
        map.add("redirect_uri", REDIRECT_URL);
        map.add("code", authorizationCode);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);

        Map<String, Object> response = restTemplate.postForObject(KAKAO_API_URL, requestEntity, HashMap.class);
        log.info("response is {}", response);

        return response.get("access_token").toString();
    }

    public KakaoUserInfo getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        log.info("getUserInfo accessToken is {}", accessToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(new LinkedMultiValueMap<>(), headers);

        String response = restTemplate.postForObject(KAKAO_USER_INFO_URL, requestEntity, String.class);
        JsonObject rootObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject properties = rootObject.getAsJsonObject("properties");
        JsonObject accountObject = rootObject.getAsJsonObject("kakao_account");

        log.info("response is {}", response);

        KakaoUserInfo kakaoUserInfo = KakaoUserInfo.builder()
                .id(rootObject.get("id").getAsString())
                .build();
                /*
                .nickname(properties.get("nickname").getAsString())
                .profileImage(properties.get("profile_image").getAsString())
                .thumbnailImage(properties.get("thumbnail_image").getAsString())
                .profileNeedsAgreement(accountObject.get("profile_needs_agreement").getAsBoolean())
                .hasEmail(accountObject.get("has_email").getAsBoolean())
                .emailNeedsAgreement(accountObject.get("email_needs_agreement").getAsBoolean())
                .isEmailValid(accountObject.get("is_email_valid").getAsBoolean())
                .isEmailVerified(accountObject.get("is_email_verified").getAsBoolean())
                .email(accountObject.get("email").getAsString())
                .hasBirthday(accountObject.get("has_birthday").getAsBoolean())
                .birthdayNeedsAgreement(accountObject.get("birthday_needs_agreement").getAsBoolean())
                .hasGender(accountObject.get("has_gender").getAsBoolean())
                .genderNeedsAgreement(accountObject.get("gender_needs_agreement").getAsBoolean())
                .gender(accountObject.get("gender").getAsString())
                */

        log.info("kakaoUserInfo is {}", kakaoUserInfo);

        return kakaoUserInfo;
    }

    public boolean vaildateToken(String token) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(KAKAO_VAILDATE_API_URL, HttpMethod.GET, requestEntity, String.class);
        if(response.getStatusCode()==HttpStatus.OK)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public String refreshToken(String refreshToken) {

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "refresh_token");
        map.add("client_id", KAKAO_API_KEY);
        map.add("refresh_token", refreshToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);

        Map<String, Object> response = restTemplate.postForObject(KAKAO_REFRESH_API_URL, requestEntity, HashMap.class);

        return response.get("access_token").toString();
    }
}
