package com.HELPT.Backend.global.auth.jwt;

import com.HELPT.Backend.global.error.CustomException;
import com.HELPT.Backend.global.error.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.security.sasl.AuthenticationException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

@Component
@Slf4j
public class JWTUtil {

    private static final Long accessTokenValidTime = Duration.ofMinutes(1).toMillis(); // 만료시간 30분
    private static final Long refreshTokenValidTime = Duration.ofDays(14).toMillis(); // 만료시간 2주
    private static final Long remainValidTime = Duration.ofDays(3).toMillis();
    private SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret){
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        String authorization= request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }
        String token = authorization.split(" ")[1];
        return token;
    }

    public Long getUserId(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userId", Long.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {
        try{
            log.info("isExpired");
            return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
        }catch (ExpiredJwtException e){
            return false;
        }catch (JwtException e){
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
    }

    public JWTToken createTokens(Long userId) {
        String accessToken = createToken(userId, accessTokenValidTime);
        String refreshToken = createToken(userId, refreshTokenValidTime);
        return new JWTToken("Bearer", accessToken, refreshToken);
    }

    public String createAccessToken(Long userId) {
        return createToken(userId, accessTokenValidTime);
    }

    public String createRefreshToken(Long userId) {
        return createToken(userId, refreshTokenValidTime);
    }

//    public void generateRefreshToken(Authentication authentication, String accessToken) {
//        String refreshToken = generateToken(authentication, REFRESH_TOKEN_EXPIRE_TIME);
//        tokenService.saveOrUpdate(authentication.getName(), refreshToken, accessToken); // redis에 저장
//    }

    public String createToken(Long userId, Long expiredMs) {

        return Jwts.builder()
                .claim("userId", userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    public JWTToken refresh(String refreshToken) {
        if (verifyRefreshToken(refreshToken)){
            Long userId = getUserId(refreshToken);
            String accessToken = createAccessToken(userId);

            Date expiration = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(refreshToken).getPayload().getExpiration();
            long diff = expiration.getTime() - System.currentTimeMillis();
            if(diff > remainValidTime){
                return new JWTToken("Bearer", accessToken, refreshToken);
            }
            return new JWTToken("Bearer", accessToken, createRefreshToken(userId));
        }
        return null;
    }

    public Boolean verifyRefreshToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true; // 토큰 검증 성공
        } catch (JwtException e) {
            throw new CustomException(ErrorCode.INVALID_REFRESH_TOKEN);
        }
    }
}
