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


    @GetMapping("/attendance/{userid}")
    public ResponseEntity<String> attendance(@PathVariable("userid") Long userid) {

        boolean bResult = memberService.attendance(userid);

        return ResponseEntity.ok(String.valueOf(bResult));
    }

    @GetMapping("/find/{userid}")
    public ResponseEntity<MemberDto> findMember(@PathVariable("userid") Long userid) {

        MemberDto resultMember = memberService.findMember(userid);

        return ResponseEntity.ok(resultMember);
    }

    @PostMapping("/register")
    public ResponseEntity<MemberDto> registerMember(@RequestBody MemberDto member,
                                                    @RequestParam("kakaoId")Long kakaoId) {

        MemberDto newMember = memberService.register(member,kakaoId);

        return ResponseEntity.ok(newMember);
    }

    @PostMapping("/update")
    public ResponseEntity<MemberDto> updateMember(@RequestBody MemberDto member) {

        MemberDto resultMemberDto = memberService.updateMember(member);

        return ResponseEntity.ok(resultMemberDto);
    }


}
