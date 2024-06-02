package com.HELPT.Backend.domain.member;

import com.HELPT.Backend.domain.member.Dto.MemberDetailResponse;
import com.HELPT.Backend.domain.member.Dto.MemberJoinResponse;
import com.HELPT.Backend.domain.member.Dto.MemberDto;
import com.HELPT.Backend.global.auth.jwt.JWTResponse;
import com.HELPT.Backend.global.common.dto.KakaoLoginRequest;
import com.HELPT.Backend.global.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.HELPT.Backend.global.auth.SecurityUtil.getCurrentUserId;

@RestController
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final S3Uploader s3Uploader;

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody KakaoLoginRequest kakaoLoginRequest) {
        return ResponseEntity.ok(memberService.login(kakaoLoginRequest));
    }

    @PostMapping(value = "/register", consumes = "multipart/form-data")
    public ResponseEntity<JWTResponse> register(
            @RequestPart("memberDto") MemberDto memberDto,
            @RequestPart("profileImage") MultipartFile profileImage){
        String uploadURL;
        try {
            uploadURL = s3Uploader.upload(profileImage, "profileFile");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(memberService.register(memberDto,uploadURL));
    }

    @GetMapping("/attendance")
    public ResponseEntity<String> attendance() {
        Long userId = getCurrentUserId();
        boolean bResult = memberService.attendance(userId);
        return ResponseEntity.ok(String.valueOf(bResult));
    }

    @GetMapping("/me")
    public ResponseEntity<MemberDto> getMyInfo() {
        Long userId = getCurrentUserId();
        MemberDto resultMember = memberService.findMember(userId);
        return ResponseEntity.ok(resultMember);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDetailResponse> findMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.findMemberDetail(memberId));
    }

    @GetMapping("/gyms/{gymId}/search")
    public ResponseEntity<List<MemberJoinResponse>> searchMembersByGym(
            @PathVariable Long gymId,
            @RequestParam String name) {
        return ResponseEntity.ok(memberService.searchMembersByGymAndName(gymId, name));
    }

    @PutMapping
    public ResponseEntity<MemberDto> updateMember(@RequestBody MemberDto member) {
        Long userId = getCurrentUserId();
        MemberDto resultMemberDto = memberService.updateMember(userId,member);

        return ResponseEntity.ok(resultMemberDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> memberRemove() {
        Long userId = getCurrentUserId();
        memberService.removeMember(userId);
        return ResponseEntity.ok().build();
    }


}
