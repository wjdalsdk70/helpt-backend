package com.HELPT.Backend.domain.member.Dto;

import com.HELPT.Backend.domain.member.Member;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    private Long userId;

    private Long gymId;

    private String userName;

    private String gender;

    private float height;

    private float weight;

    private String kakaoId;


    public Member toEntity()
    {
        return Member.builder()
                .kakaoId(kakaoId)
                .gymId(gymId)
                .userName(userName)
                .gender(gender)
                .height(height)
                .weight(weight)
                .build();
    }

    public static MemberDto toDto(Member member)
    {
        return new MemberDto(member.getUserId(),member.getGymId(),member.getUserName(),member.getGender(),member.getHeight(),member.getWeight(), member.getKakaoId());
    }
}
