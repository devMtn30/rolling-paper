package com.memorymakerpeople.memoryrollingpaper.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static String GetLoginId(HttpServletRequest request){
        HttpSession session = request.getSession();
        String loginId = (String) session.getAttribute(SessionConstants.LOGIN_MEMBER);

        return loginId;
    }
}
