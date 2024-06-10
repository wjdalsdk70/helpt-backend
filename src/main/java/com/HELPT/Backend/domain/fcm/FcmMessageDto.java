package com.HELPT.Backend.domain.fcm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * FCM 전송 Format DTO
 *
 * @author : lee
 * @fileName : FcmMessageDto
 * @since : 2/21/24
 */
@Getter
@Builder
public class FcmMessageDto {

    @JsonProperty("validate_only")
    private boolean validateOnly;

    @JsonProperty("message")
    private FcmMessageDto.Message message;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Message {

        @JsonProperty("notification")
        private FcmMessageDto.Notification notification;

        @JsonProperty("token")
        private String token;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Notification {

        @JsonProperty("title")
        private String title;

        @JsonProperty("body")
        private String body;

        @JsonProperty("image")
        private String image;
    }
}