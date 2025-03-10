package com.monmi.controller;

import com.monmi.dto.LoginDTO;
import com.monmi.service.LoginService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 토큰을받기 위해 JWT 
import com.monmi.jwt.JwtUtil;
// 쿠키 받기
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    //컨트롤러는 로그인 처리를 직접하지않고 LoginService에게 위임.
    //책임분리로 컨트롤러는 Post처리를 집중적으로 비지니스는 service가 위임.
    private final LoginService loginService;

    @PostMapping
    public String processLogin(
            @RequestParam("monami_id") String monamiId,
            @RequestParam("monami_password") String monamiPassword,
            @RequestHeader(value = "Referer", required = false) String referer, // 이전 페이지 정보 가져오기
            // main.html에서의 name = manami_id , name = manami_password 에서 입력한값을 전달.
            HttpServletResponse response // 응답 객체 (쿠키 설정을 위해 필요함...!)

    ) {
        // LoginDto 생성 ( 컨트롤러에서 받은 데이터를 DTO로 변환)
        LoginDTO loginDto = new LoginDTO();
        loginDto.setMonami_id(monamiId);
        loginDto.setMonami_password(monamiPassword);

        // 로그인 처리
        boolean isAuthenticated = loginService.login(loginDto);

        if (isAuthenticated) {
            //JWT 토큰 생성
            String token = JwtUtil.generateToken(monamiId);

            //쿠키에 JWT 저장
            Cookie cookie = new Cookie("AuthToken", token);
            cookie.setHttpOnly(true);   // JS에서 접근 불가능 (보안 강화)
            cookie.setMaxAge(60 * 60);  // 1시간 유지
            cookie.setPath("/");        // 모든 경로에서 쿠키 접근 가능
            response.addCookie(cookie);

            //리다이렉트할 때 success=true 추가
            if (referer != null && referer.contains("?")) {
                return "redirect:" + referer + "&success=true";
            } else {
                return "redirect:" + (referer != null ? referer + "?success=true" : "/");
            }
        } else {
            return "redirect:" + (referer != null ? referer + "?error=true" : "/main?error=true");
        }
    }
}










