package com.HELPT.Backend.domain.membership;
import com.HELPT.Backend.domain.membership.dto.MembershipResponse;
import com.HELPT.Backend.domain.product.Product;
import com.HELPT.Backend.global.error.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.HELPT.Backend.global.error.ErrorCode.NOT_EXIST_DATA;


@Service
@RequiredArgsConstructor
@Transactional
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public MembershipResponse findMembership(Long userid)
    {
        Membership findMembership = membershipRepository.findByUserId(userid).orElseThrow(() -> new CustomException(NOT_EXIST_DATA));
        return new MembershipResponse(findMembership);
    }

    public void removeMembership(Long membershipId)
    {
        membershipRepository.deleteById(membershipId);
    }


    public MembershipResponse addMembership(Long userId, Long productId)
    {
        Product product = membershipRepository.findProduct(productId);

        Membership addMembership = Membership.builder()
                .userId(userId)
                .gymId(product.getGymId())
                .build();

        addMembership.addDays(product.getMonths());


        Membership saveMembership = membershipRepository.save(addMembership);
        return new MembershipResponse(saveMembership);
    }

    public MembershipResponse extensionMembership(Long membershipId, LocalDate endDate)
    {
        Membership findMembership = membershipRepository.findById(membershipId).orElseThrow(() -> new CustomException(NOT_EXIST_DATA));
        findMembership.setEndDate(endDate);
        return new MembershipResponse(findMembership);
    }
}
