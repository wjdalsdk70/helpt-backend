package com.HELPT.Backend.domain.membership;

import com.HELPT.Backend.domain.membership.dto.MembershipRequest;
import com.HELPT.Backend.domain.membership.dto.MembershipResponse;
import com.HELPT.Backend.domain.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.HELPT.Backend.global.auth.SecurityUtil.getCurrentUserId;

@RestController
@RequestMapping("/memberships")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    @GetMapping("/detail")
    public ResponseEntity<Membership> membershipInfo() {

        Long userId = getCurrentUserId();

        return ResponseEntity.ok(membershipService.findMembership(userId));
    }

    @PostMapping("/purchase")
    public ResponseEntity<MembershipResponse> addMembership(@RequestParam("productId")Long productId) {

        Long userId = getCurrentUserId();
        return ResponseEntity.ok(membershipService.addMembership(userId,productId));
    }

    @PostMapping("/extension")
    public ResponseEntity<MembershipResponse> extensionMembership(
            @RequestBody MembershipRequest membershipRequest,
            @RequestParam("endDate") LocalDate endDate) {

        Long mId = membershipRequest.getMembershipId();
        return ResponseEntity.ok(membershipService.extensionMembership(mId,endDate));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Boolean> removeMembership(@RequestBody MembershipRequest membershipRequest) {

        membershipService.removeMembership(membershipRequest);
        return ResponseEntity.ok(true);
    }



}
