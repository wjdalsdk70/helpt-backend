package com.HELPT.Backend.domain.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FirebaseCloudMessageService {

//    private final SqlSession sqlSession;
//
//    public FcmServiceImpl(SqlSession ss) {
//        this.sqlSession = ss;
//    }

    public void sendMessageTo(FcmSendDto fcmSendDto) throws IOException {

        log.debug("fcmSendDto Success ");
        FcmMessageDto fcmMessageDto = makeFmd(fcmSendDto);
        log.debug("fcmMessageDto Success ");
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + getAccessToken());

        HttpEntity<FcmMessageDto> entity = new HttpEntity<>(fcmMessageDto, headers);

        String API_URL = "https://fcm.googleapis.com/v1/projects/helpt-431a3/messages:send";
        try {
            restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);
        } catch (Exception e) {
            log.error("[-] FCM 전송 오류 :: " + e.getMessage());
            log.error("[-] 오류 발생 토큰 :: [" + fcmSendDto.getToken() + "]");
            log.error("[-] 오류 발생 메시지 :: [" + fcmSendDto.getBody() + "]");
        }
    }

//    @Transactional(readOnly = true)
//    public List selectFcmSendList() {
//        FcmMapper udm = sqlSession.getMapper(FcmMapper.class);
//        return udm.selectFcmSendList();
//    }

    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "firebase/firebase_service_key.json";

        // Gson 세팅
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Duration.class, new DurationTypeAdapter())
                .setLenient().create();

        JsonReader reader = new JsonReader(new InputStreamReader(new ClassPathResource(firebaseConfigPath).getInputStream()));
        reader.setLenient(true);

        // Json파싱
        GoogleCredentials googleCredentials = gson.fromJson(reader, GoogleCredentials.class);
        googleCredentials = googleCredentials.createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

    /*
    private String makeMessage(FcmSendDto fcmSendDto) throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
        FcmMessageDto fcmMessageDto = FcmMessageDto.builder()
                .message(FcmMessageDto.Message.builder()
                        .token(fcmSendDto.getToken())
                        .notification(FcmMessageDto.Notification.builder()
                                .body(fcmSendDto.getBody())
                                .title(fcmSendDto.getTitle())
                                //.image(null)
                                .build()
                        ).build())
                //.validateOnly(false)
                .build();

        return om.writeValueAsString(fcmMessageDto);
    }
*/

    private FcmMessageDto makeFmd(FcmSendDto fcmSendDto) {

        return FcmMessageDto.builder()
                .message(FcmMessageDto.Message.builder()
                        .token(fcmSendDto.getToken())
                        .notification(FcmMessageDto.Notification.builder()
                                .body(fcmSendDto.getBody())
                                .title(fcmSendDto.getTitle())
                                //.image(null)
                                .build()
                        ).build())
                //.validateOnly(false)
                .build();
    }
}