package com.monmi.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String monami_id;

    @NotNull
    @Size(min = 8, max = 20)
    private String monami_password;

    @NotNull
    @Size(min = 1, max = 50)
    private String monami_name;

    private int dept_id;

    // 기본 생성자
    public LoginDTO() {
    }

    // 매개변수 생성자
    public LoginDTO(String monami_id, String monami_password, String monami_name, int dept_id) {
        this.monami_id = monami_id;
        this.monami_password = monami_password;
        this.monami_name = monami_name;
        this.dept_id = dept_id;
    }

    // Getter와 Setter
    public String getMonami_id() {
        return monami_id;
    }

    public void setMonami_id(String monami_id) {
        this.monami_id = monami_id;
    }

    public String getMonami_password() {
        return monami_password;
    }

    public void setMonami_password(String monami_password) {
        this.monami_password = monami_password;
    }

    public String getMonami_name() {
        return monami_name;
    }

    public void setMonami_name(String monami_name) {
        this.monami_name = monami_name;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }
}

