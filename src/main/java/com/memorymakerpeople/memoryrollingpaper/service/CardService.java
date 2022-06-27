package com.memorymakerpeople.memoryrollingpaper.service;

import com.memorymakerpeople.memoryrollingpaper.domain.Card;
import com.memorymakerpeople.memoryrollingpaper.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> getCardList(String paperId){
        return cardRepository.findByPaperId(paperId);
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }
}
