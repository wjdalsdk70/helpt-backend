package com.HELPT.Backend.domain.member.Dto;

import com.HELPT.Backend.domain.member.Member;
import com.HELPT.Backend.global.auth.GENDER;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    private Long gymId;

    private String userName;

    private GENDER gender;

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

    public static MemberDto toDto(Member member) {
        return MemberDto.builder()
                .gymId(member.getGymId())
                .userName(member.getUserName())
                .gender(member.getGender())
                .height(member.getHeight())
                .weight(member.getWeight())
                .kakaoId(member.getKakaoId())
                .build();
    }
}
