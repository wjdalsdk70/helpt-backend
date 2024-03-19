package com.HELPT.Backend.domain.member;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login/{token}")
    public ResponseEntity<String> login(@PathVariable("token") String token) {
        log.info(token);
        boolean result = memberService.checkToken(token);
        String resultString = String.valueOf(result);
        return ResponseEntity.ok(resultString);
    }

    @PostMapping("/register")
    public ResponseEntity<Member> registerMember(@RequestBody Member member) {
        Member newMember = memberService.register(member);

        log.info(newMember.toString());

        return ResponseEntity.ok(newMember);
    }


}
