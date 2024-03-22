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
    public ResponseEntity<Member> login(@RequestParam("kakaoId") Long kakaoId) {

        Member resultMember = memberService.existMember(kakaoId);

        return ResponseEntity.ok(resultMember);
    }

    @GetMapping("/attendance/{userid}")
    public ResponseEntity<String> attendance(@PathVariable("userid") int userid) {

        boolean bResult = memberService.attendance(userid);

        return ResponseEntity.ok(String.valueOf(bResult));
    }

    @GetMapping("/find/{userid}")
    public ResponseEntity<Member> findMember(@PathVariable("userid") int userid) {

        Member resultMember = memberService.findMember(userid);

        return ResponseEntity.ok(resultMember);
    }

    @PostMapping("/register")
    public ResponseEntity<Member> registerMember(@RequestBody Member member) {

        Member newMember = memberService.register(member);

        return ResponseEntity.ok(newMember);
    }

    @PostMapping("/update")
    public ResponseEntity<Member> updateMember(@RequestBody Member member) {

        Member newMember = memberService.updateMember(member);

        return ResponseEntity.ok(newMember);
    }


}
