package com.memorymakerpeople.memoryrollingpaper.controller;

import com.memorymakerpeople.memoryrollingpaper.domain.Card;
import com.memorymakerpeople.memoryrollingpaper.dto.CardRequestDto;
import com.memorymakerpeople.memoryrollingpaper.dto.CardResponseDto;
import com.memorymakerpeople.memoryrollingpaper.service.CardService;
import com.memorymakerpeople.memoryrollingpaper.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/Card")
public class CardController {

    @Autowired
    private CardService cardService;
    private SessionUtils sessionUtils;
    @PostMapping
    public Card createCard(Card card, HttpServletRequest request, HttpServletResponse response){
        String loginId = sessionUtils.GetLoginId(request);
        if (loginId.isEmpty()){
            return null;
        }
        return cardService.createCard(card);
    }

}
