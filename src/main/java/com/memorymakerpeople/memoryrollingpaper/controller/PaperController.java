package com.memorymakerpeople.memoryrollingpaper.controller;

import com.memorymakerpeople.memoryrollingpaper.domain.Paper;
import com.memorymakerpeople.memoryrollingpaper.dto.PaperRequestDto;
import com.memorymakerpeople.memoryrollingpaper.service.PaperService;
import com.memorymakerpeople.memoryrollingpaper.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;
    SessionUtils sessionUtils = new SessionUtils();

    @PostMapping
    public PaperRequestDto createPaper(PaperRequestDto paperRequestDto, HttpServletRequest request, HttpServletResponse response){
        PaperRequestDto result = new PaperRequestDto();
        System.out.println("PaperController.createPaper");
        System.out.println("paperRequestDto = " + paperRequestDto);
        Paper paper = new Paper();
        String loginId = sessionUtils.GetLoginId(request);
        if (loginId.isEmpty()){
            result.statusCode = "fail";
            result.message = "not found user";
        }else{
            paper.setUserId(loginId);
            paper.setPaperTitle(paperRequestDto.getPaperTitle());
            paper.setDueDt(paperRequestDto.getDueDt());
            paper.setTheme(paperRequestDto.getTheme());
            paper.setPaperUrl(UUID.randomUUID().toString());
            result = paperService.createPaper(paper);
        }

        return result;
    }

    @GetMapping
    public List<Paper> paper(HttpServletRequest request, HttpServletResponse response){
        Paper paper = new Paper();
        String loginId = sessionUtils.GetLoginId(request);
        paper.setUserId(loginId);
        return paperService.selectPaper(paper);
    }

    @GetMapping("/paperDetail")
    public PaperRequestDto paperDetail(PaperRequestDto paperRequestDto, HttpServletRequest request, HttpServletResponse response){
        Paper paper = new Paper();
        PaperRequestDto result = new PaperRequestDto();
        return result;
    }

}
