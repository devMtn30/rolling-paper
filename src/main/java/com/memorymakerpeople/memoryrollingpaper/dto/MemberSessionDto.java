package com.memorymakerpeople.memoryrollingpaper.dto;

import com.memorymakerpeople.memoryrollingpaper.domain.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class MemberSessionDto implements Serializable {
    private String username;
    private String password;
    private String nickname;
    private String email;

    /* Entity -> Dto */
    public MemberSessionDto(Member member) {
        this.username = member.getUsername();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
    }
}