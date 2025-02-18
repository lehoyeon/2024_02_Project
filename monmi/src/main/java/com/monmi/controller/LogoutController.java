package com.monmi.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    //로그아웃 처리
    @GetMapping
    public String logout(HttpServletResponse response) {
        //쿠키 삭제 (쿠키 만료 시간을 0으로 설정)
        Cookie cookie = new Cookie("AuthToken", "");
        cookie.setHttpOnly(true);   // JS에서 접근 불가능 (보안 강화)
        cookie.setMaxAge(0);        // 즉시 만료
        cookie.setPath("/");        // 모든 경로에서 쿠키 접근 가능
        response.addCookie(cookie);

        return "redirect:/login";
    }
}

