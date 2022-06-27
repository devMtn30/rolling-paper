package com.memorymakerpeople.memoryrollingpaper.service;

import com.memorymakerpeople.memoryrollingpaper.domain.Member;
import com.memorymakerpeople.memoryrollingpaper.dto.DefaultResponseDto;
import com.memorymakerpeople.memoryrollingpaper.dto.MemberRequestDto;
import com.memorymakerpeople.memoryrollingpaper.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    //회원가입
    public DefaultResponseDto joinUser(MemberRequestDto memberRequest) {
        Member member = new Member();
        DefaultResponseDto result = new DefaultResponseDto();
        //회원가입 여부 확인
        if(isUser(memberRequest)) {
            //유저면 토큰 확인 후 토큰 발급
            result.statusCode = "complete";
            result.message = "login";
        }else{
            //회원 가입 후 토큰 발급
            member.setUsername(memberRequest.getId());
            Member save = memberRepository.save(member);
            if (save.getUsername().isEmpty()){
                result.statusCode = "fail";
            }else{
                result.statusCode = "complete";
            }
            result.message = "register";
        }

        return result;
    }

    public boolean isUser(MemberRequestDto memberRequest){
        Member member = new Member();
        Optional<Member> result = memberRepository.findByUsername(memberRequest.getId());
        System.out.println("!result.isEmpty() = " + !result.isEmpty());
        return !result.isEmpty();
    }

}
