package com.HELPT.Backend.domain.qr;

import com.HELPT.Backend.domain.member.MemberRepository;
import com.HELPT.Backend.domain.membership.Membership;
import com.HELPT.Backend.domain.membership.dto.MembershipResponse;
import com.HELPT.Backend.global.auth.jwt.JWTUtil;
import com.HELPT.Backend.global.error.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.HELPT.Backend.global.error.ErrorCode.NOT_EXIST_MEMBER;

@Service
@RequiredArgsConstructor
public class QrCodeService {

    private final JWTUtil jwtUtil;
    private final MemberRepository memberRepository;

    @Transactional
    public String addQrCode(Long userId){
        return jwtUtil.createQrToken(userId);
    }

    @Transactional
    public MembershipResponse verifyQrCode(Long userId, Long gymId){
//        jwtUtil.isExpired(qrToken);
//        Long userId = jwtUtil.getUserId(qrToken);
        Membership membership = memberRepository.qrVelify(userId,gymId);
        if(membership==null) {
            throw new CustomException(NOT_EXIST_MEMBER);
        }
        if(!membership.getLastAttendDate().equals(LocalDate.now())){
            membership.attend(membership.getAttendanceDate()+1);
        }
        return MembershipResponse.builder().membership(membership).build();
    }
}
