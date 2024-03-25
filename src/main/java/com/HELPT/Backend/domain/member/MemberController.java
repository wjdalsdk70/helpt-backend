package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.domain.member.Dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


}
