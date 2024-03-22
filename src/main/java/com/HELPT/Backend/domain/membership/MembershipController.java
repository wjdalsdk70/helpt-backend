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


    @GetMapping("/find/{userid}")
    public ResponseEntity<Membership> getMembership(@PathVariable("userid") int userid)
    {
        Membership resultMembership = membershipService.findMembership(userid);
        return ResponseEntity.ok(resultMembership);
    }

    @PostMapping("/register")
    public ResponseEntity<Membership> addMembership(@RequestBody Membership membership)
    {
        Membership resultMembership = membershipService.addMembership(membership);
        return ResponseEntity.ok(resultMembership);
    }

}
