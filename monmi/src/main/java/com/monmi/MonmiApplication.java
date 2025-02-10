package com.monmi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.monmi.sales.mapper")
@SpringBootApplication
public class MonmiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonmiApplication.class, args);
	}

}
