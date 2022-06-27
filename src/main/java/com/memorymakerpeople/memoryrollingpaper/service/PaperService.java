package com.memorymakerpeople.memoryrollingpaper.service;

import com.memorymakerpeople.memoryrollingpaper.domain.Paper;
import com.memorymakerpeople.memoryrollingpaper.dto.PaperRequestDto;
import com.memorymakerpeople.memoryrollingpaper.dto.PaperResponseDto;
import com.memorymakerpeople.memoryrollingpaper.repository.CardRepository;
import com.memorymakerpeople.memoryrollingpaper.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private CardRepository cardRepository;

    public PaperResponseDto createPaper(Paper paper) {
        PaperResponseDto result = new PaperResponseDto();
        Paper save = paperRepository.save(paper);
        if (save == null){
            result.statusCode = "fail";
            result.message = "페이지 생성";
        }else{
            result.statusCode = "complete";
            result.message = "페이지 생성";
        }

        return result;
    }

    public List<Paper> selectPaper(Paper paper){
        return paperRepository.findByUserId(paper.getUserId());
    }

    public PaperResponseDto selectOnePaper(PaperRequestDto paper){
        PaperResponseDto paperResponseDto = new PaperResponseDto();
        String paperId = String.valueOf(paper.getPaperId());
        paperResponseDto.setCardList(cardRepository.findByPaperId(paperId));
        /*paperResponseDto.setUserId(paper.getUserId());
        paperResponseDto.setPaperUrl(paper.getPaperUrl());
        paperResponseDto.setPaperTitle(paper.getPaperTitle());
        paperResponseDto.setPaperId(paper.getPaperId());
        paperResponseDto.setDueDt(paper.getDueDt());
        paperResponseDto.setTheme(paper.getTheme());
        paperResponseDto.setOpenStatus(paper.getOpenStatus());*/
        return paperResponseDto;
    }
}
