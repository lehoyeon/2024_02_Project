package com.monmi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//vo에서는 getter를 사용하지만 dto에서는 @Setter를 사용
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="monami_login")
public class Login {
// 보통 class 이름으로 DB이름을 그대로 가져오는데
// Login이라는 DB테이블은 없기때문에 따로지정해서 사용
    @Id //primary key 값을 위해 필요
    @Column(length = 50 ,nullable = false)
    private String monami_id;
    @Column(length = 50 ,nullable = false)
    private String monami_password;
    @Column(length = 50 ,nullable = false)
    private String monami_name;
    @Column
    private int dept_id;
}
