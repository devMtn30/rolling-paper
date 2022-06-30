package com.memorymakerpeople.memoryrollingpaper.dto;

import lombok.Data;

@Data
public class MemberResponseDto extends DefaultResponseDto {
    public Integer id;
    public String username;//아이디
    private String nickname;
    private String email;

}
