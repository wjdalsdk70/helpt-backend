package com.HELPT.Backend.domain.membership;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memberships")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    /*
    @GetMapping("/{userid}")
    public ResponseEntity<Membership> getMembership(@PathVariable("userid") int userid)
    {

    }
    */
}
