package com.HELPT.Backend.global.auth.service;

import com.HELPT.Backend.domain.gym.dto.GymResponse;
import com.HELPT.Backend.domain.member.Dto.MemberDto;
import com.HELPT.Backend.domain.member.Member;
import com.HELPT.Backend.domain.member.MemberRepository;
import com.HELPT.Backend.global.auth.jwt.JWTResponse;
import com.HELPT.Backend.global.auth.jwt.JWTToken;
import com.HELPT.Backend.global.auth.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthService {

    private final MemberRepository memberRepository;
    private final JWTUtil jwtUtil;

    public JWTResponse login(MemberDto memberDto) {
        try {
            String kakaoId = memberDto.getKakaoId();
            Optional<Member> existingMemberOptional = memberRepository.findByKakaoId(kakaoId);

            Member member;
            if (existingMemberOptional.isPresent()) {
                member = existingMemberOptional.get();
            } else {
                memberRepository.save(memberDto.toEntity());
                member = memberRepository.findByKakaoId(kakaoId).orElseThrow(() -> new RuntimeException("Member could not be retrieved after save."));
            }

            JWTToken jwt = jwtUtil.createTokens(member.getUserId());
            return JWTResponse.builder().token(jwt).build();
        } catch (Exception e) {
            log.info("Login error", e);
        }
        return null;
    }
}