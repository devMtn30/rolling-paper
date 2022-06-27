package com.memorymakerpeople.memoryrollingpaper.controller;

import com.memorymakerpeople.memoryrollingpaper.domain.Paper;
import com.memorymakerpeople.memoryrollingpaper.dto.PaperRequestDto;
import com.memorymakerpeople.memoryrollingpaper.dto.PaperResponseDto;
import com.memorymakerpeople.memoryrollingpaper.service.PaperService;
import com.memorymakerpeople.memoryrollingpaper.util.SessionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PaperResponseDto createPaper(Paper paper, HttpServletRequest request, HttpServletResponse response){
        PaperResponseDto result = new PaperResponseDto();
        String loginId = sessionUtils.GetLoginId(request);
        if (loginId.isEmpty()){
            result.statusCode = "fail";
            result.message = "not found user";
        }else{
            paper.setPaperUrl(UUID.randomUUID().toString());
            paper.setUserId(loginId);
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
    public List<Paper> paper(HttpServletRequest request, HttpServletResponse response){
        String loginId = sessionUtils.GetLoginId(request);
        if (loginId.isEmpty()){
            return null;
        }
        Paper paper = new Paper();
        paper.setUserId(loginId);
        return paperService.selectPaper(paper);
    }

    @GetMapping("/paperDetail")
    @ApiOperation(value = "롤링페이퍼 보기", notes = "하나의 롤링페이퍼를 조회합니다.")
    public PaperResponseDto paperDetail(PaperRequestDto paperRequestDto, HttpServletRequest request, HttpServletResponse response){
        String loginId = sessionUtils.GetLoginId(request);
        if (loginId.isEmpty()){
            return null;
        }
        paperRequestDto.setUserId(sessionUtils.GetLoginId(request));
        return paperService.selectOnePaper(paperRequestDto);
    }

}
