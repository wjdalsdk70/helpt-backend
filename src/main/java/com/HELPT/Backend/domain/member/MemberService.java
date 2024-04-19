package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.domain.member.Dto.MemberDto;
import com.HELPT.Backend.domain.membership.Membership;
import com.HELPT.Backend.global.auth.jwt.JWTResponse;
import com.HELPT.Backend.global.auth.jwt.JWTToken;
import com.HELPT.Backend.global.auth.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

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

    public boolean attendance(Long userId)
    {
        Membership membership =  memberRepository.attendance(userId);
        if(membership==null) return false;

        membership.setAttendanceDate(membership.getAttendanceDate()+1);

        return true;
    }
    public MemberDto findMember(Long userId)
    {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new RuntimeException("Member not found"));
        MemberDto memberDto = MemberDto.toDto(member);
        return memberDto;
    }

    public MemberDto updateMember(Long userId,MemberDto member)
    {
        Member findMember = memberRepository.findById(userId).orElseThrow(() -> new RuntimeException("Member not found"));
        findMember.setHeight(member.getHeight());
        findMember.setWeight(member.getWeight());
        MemberDto resultDto = MemberDto.toDto(findMember);

        return resultDto;
    }

    public MemberDto register(MemberDto member)
    {
        Member newMember = Member.builder()
                .gymId(member.getGymId())
                .kakaoId(member.getKakaoId())
                .userName(member.getUserName())
                .gender(member.getGender())
                .height(member.getHeight())
                .weight(member.getWeight())
                .build();
        memberRepository.save(newMember);

        MemberDto newMemberDto = MemberDto.toDto(newMember);
        return newMemberDto;

    }

    @Transactional
    public void removeMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Gym not found"));
        memberRepository.delete(member);
    }



}
