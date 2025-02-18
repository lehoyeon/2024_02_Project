package com.monmi.service;

import com.monmi.domain.Login;
import com.monmi.dto.LoginDTO;
import com.monmi.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public boolean login(LoginDTO loginDto) {
        // 1. 아이디로 사용자 조회
        Optional<Login> optionalLogin = loginRepository.findById(loginDto.getMonami_id());

        if (optionalLogin.isPresent()) { // 사용자가 존재하는 경우
            Login login = optionalLogin.get();
            // 2. 비밀번호 비교
            return login.getMonami_password().equals(loginDto.getMonami_password());
        }
        return false; // 아이디가 없거나 비밀번호가 불일치하면 로그인 실패
    }


    @Override
    public Login register(LoginDTO loginDto) {
        // DTO를 Entity로 변환
        // 로그인에서 쓸 id와 password만 가져오기.
        Login login = new Login();
        login.setMonami_id(loginDto.getMonami_id());
        login.setMonami_password(loginDto.getMonami_password()); // 필요시 암호화 추가

        //login.setRole("USER"); // 주석처리: 역활설정 domain(vo)에서 role 참조

        // DB에 저장
        return loginRepository.save(login);
    }
}
