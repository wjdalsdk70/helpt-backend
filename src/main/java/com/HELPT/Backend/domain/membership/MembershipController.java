package com.HELPT.Backend.domain.membership;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memberships")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;
    @GetMapping("/find/{userId}")
    public ResponseEntity<Membership> getMembership(@PathVariable("userId") Long userId)
    {
        Membership resultMembership = membershipService.findMembership(userId);
        return ResponseEntity.ok(resultMembership);
    }

    @PostMapping("/register")
    public ResponseEntity<Membership> addMembership(@RequestBody Membership membership)
    {
        Membership resultMembership = membershipService.addMembership(membership);
        return ResponseEntity.ok(resultMembership);
    }

    @PostMapping("/extension")
    public ResponseEntity<Membership> extensionMembership(@RequestParam Long userId,@RequestParam int addDays)
    {
        Membership resultMembership = membershipService.extensionMembership(userId,addDays);
        return ResponseEntity.ok(resultMembership);
    }

}
