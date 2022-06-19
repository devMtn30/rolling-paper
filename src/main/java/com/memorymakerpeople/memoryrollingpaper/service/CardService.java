package com.memorymakerpeople.memoryrollingpaper.service;

import com.memorymakerpeople.memoryrollingpaper.domain.Card;
import com.memorymakerpeople.memoryrollingpaper.repository.CardRepository;
import com.memorymakerpeople.memoryrollingpaper.repository.PaperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> getCardList(int paperId){
        return cardRepository.findByPaperId(paperId);
    }
}
