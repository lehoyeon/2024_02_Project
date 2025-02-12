package com.monmi.service;

import com.monmi.domain.Department;
import com.monmi.domain.Signin;
import com.monmi.dto.SigninDTO;
import com.monmi.repository.SigninRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SigninService {

    private final SigninRepository signinRepository;
    private final DepartmentService departmentService; // ✅ DepartmentService 사용

    // ✅ 생성자에서 의존성 주입
    @Autowired
    public SigninService(SigninRepository signinRepository, DepartmentService departmentService) {
        this.signinRepository = signinRepository;
        this.departmentService = departmentService;  // ✅ 필드 초기화
    }



    // ✅ 회원가입 처리
    public SigninDTO registerUser(SigninDTO dto) {
        // ❌ ID가 비어있으면 예외 발생 (필수 입력값)
        if (dto.getSignin_id() == null || dto.getSignin_id().trim().isEmpty()) {
            throw new IllegalArgumentException("ID를 입력해야 합니다.");
        }
        if (dto.getSignin_password() == null || dto.getSignin_password().trim().isEmpty()) {
            throw new IllegalArgumentException("비밀번호를 입력해야 합니다.");
        }
        if (dto.getSignin_name() == null || dto.getSignin_name().trim().isEmpty()) {
            throw new IllegalArgumentException("이름을 입력해야 합니다.");
        }

        // ✅ 중복 확인: 동일한 ID가 이미 있는지 체크
        if (signinRepository.existsById(dto.getSignin_id())) {
            throw new IllegalStateException("이미 사용 중인 ID입니다.");
        }

        // ✅ departmentService를 사용하여 부서 ID 조회
        Department department = departmentService.getDepartmentById(dto.getSignin_deft_id());

        // ✅ DTO → 엔티티 변환 후 저장
        Signin signin = new Signin(
                dto.getSignin_id(),
                dto.getSignin_password(),
                dto.getSignin_name(),
                department
        );

        signinRepository.save(signin);
        return dto;
    }
}
