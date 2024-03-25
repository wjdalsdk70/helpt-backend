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
    public ResponseEntity<MemberDto> login(@RequestParam("kakaoId") Long kakaoId) {
        MemberDto resultMember = memberService.existMember(kakaoId);

        return ResponseEntity.ok(resultMember);
    }


    @GetMapping("/attendance/{userId}")
    public ResponseEntity<String> attendance(@PathVariable("userId") Long userId) {

        boolean bResult = memberService.attendance(userId);

        return ResponseEntity.ok(String.valueOf(bResult));
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<MemberDto> findMember(@PathVariable("userId") Long userId) {

        MemberDto resultMember = memberService.findMember(userId);

        return ResponseEntity.ok(resultMember);
    }

    @PostMapping("/register")
    public ResponseEntity<MemberDto> registerMember(@RequestBody MemberDto member,
                                                    @RequestParam("kakaoId")Long kakaoId) {

        MemberDto resultMember = memberService.register(member,kakaoId);

        return ResponseEntity.ok(resultMember);
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable("userId")Long userId,@RequestBody MemberDto member) {

        MemberDto resultMember = memberService.updateMember(userId,member);

        return ResponseEntity.ok(resultMember);
    }


}
