package com.HELPT.Backend.domain.manager.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoLoginRequest {

    private String kakaoId;
}
