package com.monmi.controller;

import com.monmi.jwt.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/check")
public class CheckController {

    @GetMapping
    public Map<String, Boolean> checkLoginStatus(@CookieValue(value = "AuthToken", defaultValue = "") String authToken) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("isLoggedIn", !authToken.isEmpty() && JwtUtil.isTokenValid(authToken)); // ✅ JSON 반환
        return response;
    }
}


