package com.HELPT.Backend.domain.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private static MemberRepository memberRepository;

    public boolean checkToken(String tk)
    {
        return memberRepository.isToken(tk);
    }

}
