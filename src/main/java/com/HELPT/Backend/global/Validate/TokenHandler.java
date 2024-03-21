package com.HELPT.Backend.global.Validate;

import com.HELPT.Backend.domain.library.kakaomodule.KakaoAPI;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenHandler implements HandlerInterceptor {

    @Autowired
    private static KakaoAPI kakaoAPI;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            //1. 유효한 토큰
            if (kakaoAPI.vaildateToken(token)) {
                return true; // 유효한 토큰
            }
        }
        //에러 코드 401
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("text/plain");
        String errorMessage = "만료된 토큰입니다.";
        response.getWriter().write(errorMessage);

        return false;
    }
}
