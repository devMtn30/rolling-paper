package com.memorymakerpeople.memoryrollingpaper.service;

import com.memorymakerpeople.memoryrollingpaper.domain.Member;
import com.memorymakerpeople.memoryrollingpaper.dto.DefaultResponseDto;
import com.memorymakerpeople.memoryrollingpaper.dto.MemberRequestDto;
import com.memorymakerpeople.memoryrollingpaper.dto.MemberResponseDto;
import com.memorymakerpeople.memoryrollingpaper.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    //회원가입
    public MemberResponseDto joinUser(MemberRequestDto memberRequest) {
        Member member = new Member();
        MemberResponseDto result = new MemberResponseDto();
        //회원가입 여부 확인
        Optional<Member> user = isUser(memberRequest);
        if(user.isPresent()) {
            //유저면 토큰 확인 후 토큰 발급
            result.statusCode = "complete";
            result.message = "login";
            if(user.get().getNickname().isEmpty()){
                    result.setNickname(user.get().getNickname());
                }
        }else{
            //회원 가입 후 토큰 발급
            member.setUsername(memberRequest.getUsername());
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

    public Optional<Member> isUser(MemberRequestDto memberRequest){
        Member member = new Member();
        return memberRepository.findByUsername(memberRequest.getUsername());
    }

    public MemberResponseDto setNickname(MemberRequestDto memberRequestDto) {
        MemberResponseDto result = new MemberResponseDto();
        Member member = new Member();
        member.setNickname(memberRequestDto.getNickname());
        Member save = memberRepository.save(member);
        if (save.getUsername().isEmpty()){
            result.statusCode = "fail";
        }else{
            result.statusCode = "complete";
        }
        result.message = "register";
        return  result;
    }
}
