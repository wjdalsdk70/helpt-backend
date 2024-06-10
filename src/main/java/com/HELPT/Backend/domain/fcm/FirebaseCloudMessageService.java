package com.HELPT.Backend.domain.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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

        log.debug("fcmSendDto :: " + fcmSendDto);
        FcmMessageDto fcmMessageDto = makeFmd(fcmSendDto);
        log.debug("fcmMessageDto :: " + fcmMessageDto);
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

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

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