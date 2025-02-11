package com.monmi.service;

import com.monmi.domain.Login;
import com.monmi.dto.LoginDTO;

public interface LoginService {
    boolean login(LoginDTO loginDto);         // 로그인 검증

    Login register(LoginDTO loginDto);

}
