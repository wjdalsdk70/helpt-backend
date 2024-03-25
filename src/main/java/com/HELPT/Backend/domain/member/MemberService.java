package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.domain.membership.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto existMember(Long kakaoId)
    {
        Member member = memberRepository.findByKakaoId(kakaoId).orElseThrow(() -> new RuntimeException("Member not found"));
        MemberDto memberDto = MemberDto.toDto(member);
        return memberDto;
    }

    public boolean attendance(Long userid)
    {
        Membership membership =  memberRepository.attendance(userid);
        if(membership==null) return false;

        membership.setAttendanceDate(membership.getAttendanceDate()+1);

        return true;
    }
    public MemberDto findMember(Long userid)
    {
        Member member = memberRepository.findById(userid).orElseThrow(() -> new RuntimeException("Member not found"));
        MemberDto memberDto = MemberDto.toDto(member);
        return memberDto;
    }

    public MemberDto updateMember(MemberDto member)
    {
        Member findMember = memberRepository.findById(member.getUserId()).orElseThrow(() -> new RuntimeException("Member not found"));
        findMember.setHeight(member.getHeight());
        findMember.setWeight(member.getWeight());
        MemberDto resultDto = MemberDto.toDto(findMember);

        return resultDto;
    }

    public MemberDto register(MemberDto member,Long kakaoId)
    {
        Member newMember = Member.builder()
                .gymId(member.getGymId())
                .kakaoId(kakaoId)
                .userName(member.getUserName())
                .gender(member.getGender())
                .height(member.getHeight())
                .weight(member.getWeight())
                .build();
        memberRepository.save(newMember);

        MemberDto newMemberDto = MemberDto.toDto(newMember);
        return newMemberDto;

    }



}
