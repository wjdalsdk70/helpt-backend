package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.domain.membership.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member existMember(Long kakaoId)
    {
        Member member = memberRepository.findByKakaoId(kakaoId).orElseThrow(() -> new RuntimeException("Member not found"));
        return member;
    }

    public boolean attendance(Long userid)
    {
        Membership membership =  memberRepository.attendance(userid);

        if(membership==null) return false;
        membership.setAttendanceDate(membership.getAttendanceDate()+1);

        return true;
    }
    public Member findMember(Long userid)
    {
        Member member = memberRepository.findById(userid).orElseThrow(() -> new RuntimeException("Member not found"));
        return member;
    }

    public Member updateMember(Member member)
    {
        Member findMember = memberRepository.findById(member.getUserId()).orElseThrow(() -> new RuntimeException("Member not found"));
        BeanUtils.copyProperties(member,findMember);

        return findMember;
    }

    public Member register(Member member)
    {
        Member newMember = memberRepository.save(member);

        return newMember;

    }



}
