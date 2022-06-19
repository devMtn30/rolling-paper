package com.memorymakerpeople.memoryrollingpaper.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class PaperRequestDto extends DefaultResponseDto{
    private int paperId;
    private String paperTitle;
    private Timestamp dueDt;
    private String theme;
    private String paperUrl;
    private String deleteYn;
    private String openStatus;
    private String userId;
}
