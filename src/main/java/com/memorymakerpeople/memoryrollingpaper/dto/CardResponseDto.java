package com.memorymakerpeople.memoryrollingpaper.dto;

import com.memorymakerpeople.memoryrollingpaper.domain.Card;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CardResponseDto extends DefaultResponseDto{
    private int cardId;
    private String cardText;
    private String cardColor;
    private String paperId;
    private String fontStyle;
    private String fontColor;
}
