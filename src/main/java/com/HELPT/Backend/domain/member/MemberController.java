package com.HELPT.Backend.domain.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private static MemberService memberService;

    @PostMapping("/login")
    public boolean login(@RequestBody String token)
    {
        return memberService.checkToken(token);

    }
}
