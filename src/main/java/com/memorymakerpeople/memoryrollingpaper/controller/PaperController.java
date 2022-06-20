package com.memorymakerpeople.memoryrollingpaper.controller;

import com.memorymakerpeople.memoryrollingpaper.domain.Paper;
import com.memorymakerpeople.memoryrollingpaper.dto.PaperRequestDto;
import com.memorymakerpeople.memoryrollingpaper.dto.PaperResponseDto;
import com.memorymakerpeople.memoryrollingpaper.service.PaperService;
import com.memorymakerpeople.memoryrollingpaper.util.SessionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = {"롤링페이퍼 관리 API"})
public class PaperController {

    @Autowired
    private PaperService paperService;
    SessionUtils sessionUtils = new SessionUtils();

    @PostMapping
    @ApiOperation(value = "롤링페이퍼 생성", notes = "현재 로그인된 아이디를 기준으로 롤링페이퍼를 생성 합니다.")
    public PaperRequestDto createPaper(PaperRequestDto paperRequestDto, HttpServletRequest request, HttpServletResponse response){
        PaperRequestDto result = new PaperRequestDto();
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
            if(result.getPaperId() < 0){
                result.statusCode = "fail";
                result.message = "crate paper";
            }
        }

        return result;
    }

    @GetMapping
    @ApiOperation(value = "롤링페이퍼 목록", notes = "현재 로그인된 아이디를 기준으로 생성된 롤링페이퍼를 조회합니다.")
    public List<PaperResponseDto> paper(HttpServletRequest request, HttpServletResponse response){
        Paper paper = new Paper();
        String loginId = sessionUtils.GetLoginId(request);
        paper.setUserId(loginId);
        return paperService.selectPaper(paper);
    }

    @GetMapping("/paperDetail")
    @ApiOperation(value = "롤링페이퍼 보기", notes = "하나의 롤링페이퍼를 조회합니다.")
    public PaperResponseDto paperDetail(PaperRequestDto paperRequestDto, HttpServletRequest request, HttpServletResponse response){
        paperRequestDto.setUserId(sessionUtils.GetLoginId(request));
        return paperService.selectOnePaper(paperRequestDto);
    }

}
