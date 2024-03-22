package com.HELPT.Backend.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member existMember(Long kakaoId)
    {
        return memberRepository.findByKakaoId(kakaoId);
    }

    public Member findMember(int userid)
    {
        return memberRepository.findById(userid);
    }
    public Member updateMember(Member member)
    {
        return memberRepository.update(member);
    }
    public boolean attendance(int userid)
    {
        return memberRepository.attendance(userid);
    }
    public Member register(Member member)
    {
        Member newMember = new Member();
        newMember.setGender(member.getGender());
        newMember.setUserName(member.getUserName());
        newMember.setWeight(member.getWeight());
        newMember.setHeight(member.getHeight());

        return memberRepository.add(newMember);

    }



}
