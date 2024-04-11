package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.domain.member.Dto.MemberDto;
import com.HELPT.Backend.global.auth.jwt.JWTResponse;
import com.HELPT.Backend.global.kakaomodule.KakaoAPI;
import com.HELPT.Backend.global.kakaomodule.KakaoUserInfo;
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

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody MemberDto memberDto) {
        log.info("login");
        return ResponseEntity.ok(memberService.login(memberDto));
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
                                                    @RequestParam("kakaoId")String kakaoId) {
        MemberDto newMember = memberService.register(member,kakaoId);
        return ResponseEntity.ok(newMember);
    }

    @PostMapping("/update")
    public ResponseEntity<MemberDto> updateMember(@PathVariable("userid") Long userid,
                                                  @RequestBody MemberDto member) {

        MemberDto resultMemberDto = memberService.updateMember(userid,member);

        return ResponseEntity.ok(resultMemberDto);
    }


}
