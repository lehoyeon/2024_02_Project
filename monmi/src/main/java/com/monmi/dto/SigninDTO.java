package com.monmi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor

public class SigninDTO {
    
    @Size(min = 5, max = 15, message = "아이디는 5자 이상 15자 이내로 입력해주세요")
    private String signin_id;
    
    @Size(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이내로 입력해주세요")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "비밀번호는 영문자와 숫자를 포함해야 합니다.")
    private String signin_password;

    private String signin_name;

    private Integer signin_deft_id;



}
