package com.monmi.domain;

import javax.persistence.*;

@Entity
@Table(name = "monami_login")
public class Signin {

    @Id
    @Column(name = "monami_id")
    private String signin_id;

    @Column(name = "monami_password")
    private String signin_password;

    @Column(name = "monami_name")
    private String signin_name;

    //Foreing key 관계 설정하기.
    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "department_id") // 컬럼명 일치해야 함
    private Department department;

    @Column(name ="monami_position", nullable = true)
    private String monami_position;

    // 기본 생성자 (JPA용)
    public Signin() {}

    // 필요한 모든 필드를 받는 생성자 추가
    public Signin(String signin_id, String signin_password, String signin_name,Department department) {
        this.signin_id = signin_id;
        this.signin_password = signin_password;
        this.signin_name = signin_name;
        this.department = department;
    }


}
