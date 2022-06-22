package com.memorymakerpeople.memoryrollingpaper.dto;

import lombok.Data;

@Data
public class CardRequestDto{
    private int cardId;
    private String cardText;
    private String cardColor;
    private String paperId;
    private String fontStyle;
    private String fontColor;
}
