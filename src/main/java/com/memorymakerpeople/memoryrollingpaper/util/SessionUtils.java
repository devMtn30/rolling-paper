package com.memorymakerpeople.memoryrollingpaper.util;

import com.memorymakerpeople.memoryrollingpaper.domain.Member;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static String GetLoginId(HttpServletRequest request){
        HttpSession session = request.getSession();
        String loginId = (String) session.getAttribute(SessionConstants.LOGIN_MEMBER);

        return loginId;
    }
}
