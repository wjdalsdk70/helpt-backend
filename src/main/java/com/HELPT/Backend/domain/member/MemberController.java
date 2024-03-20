package com.HELPT.Backend.domain.member;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public ResponseEntity<Member> login(@RequestParam("token") String token) {
        log.info(token);

        //TODO:: 카카오 서버로 code전송해서 인증토큰 받은 뒤 DB 체크 후 null or Member (with Auth Info) 전송함

        Member resultMember = memberService.checkToken(token);
        return ResponseEntity.ok(resultMember);
    }

    @PostMapping("/register")
    public ResponseEntity<Member> registerMember(@RequestBody Member member) {

        Member newMember = memberService.register(member);
        return ResponseEntity.ok(newMember);
    }


}
