package com.memorymakerpeople.memoryrollingpaper.controller;

import com.memorymakerpeople.memoryrollingpaper.domain.Member;
import com.memorymakerpeople.memoryrollingpaper.dto.DefaultResponseDto;
import com.memorymakerpeople.memoryrollingpaper.dto.MemberRequestDto;
import com.memorymakerpeople.memoryrollingpaper.service.MemberService;
import com.memorymakerpeople.memoryrollingpaper.util.JwtProvider;
import com.memorymakerpeople.memoryrollingpaper.util.SessionConstants;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public DefaultResponseDto register(MemberRequestDto memberRequestDto, HttpServletRequest request, HttpServletResponse response) throws Exception{
        DefaultResponseDto result = memberService.joinUser(memberRequestDto);
        if (result.message == "register" && result.statusCode == "complete") {
            result = memberService.joinUser(memberRequestDto);
            HttpSession session = request.getSession();                         // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 반환
            session.setAttribute(SessionConstants.LOGIN_MEMBER, memberRequestDto.getId());   // 세션에 로그인 회원 정보 보관
            session.setMaxInactiveInterval(1800); // 1800초
            response.setStatus(HttpStatus.OK.value());
        }
        else if (result.message == "login" && result.statusCode == "complete") {
            // 로그인 성공 처리
            HttpSession session = request.getSession();                         // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 반환
            session.setAttribute(SessionConstants.LOGIN_MEMBER, memberRequestDto.getId());   // 세션에 로그인 회원 정보 보관
            session.setMaxInactiveInterval(1800); // 1800초
            response.setStatus(HttpStatus.OK.value());
        }
        return result;
    }

    @PostMapping("/logout")
    public DefaultResponseDto logout(HttpServletRequest request) {
        DefaultResponseDto result = new DefaultResponseDto();

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();   // 세션 날림
            result.statusCode = "complete";
            result.message = "logout";
        }

        return result;
    }


}
