package com.memorymakerpeople.memoryrollingpaper.dto;

import lombok.Data;

@Data
public class MemberRequestDto{
    private String id;
    private String accessToken;
    private String refreshToken;

}
