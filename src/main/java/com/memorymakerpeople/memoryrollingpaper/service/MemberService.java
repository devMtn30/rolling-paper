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
        if(!user.isEmpty()) {
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
        //옵셔널 값 가져오기
        result.setNickname();

        return result;
    }

    public Optional<Member> isUser(MemberRequestDto memberRequest){
        Member member = new Member();
        return memberRepository.findByUsername(memberRequest.getId());
    }

}
