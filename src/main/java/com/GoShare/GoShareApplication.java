package com.GoShare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//글 등록, 수정 시간 자동 업데이트
@EnableJpaAuditing
@SpringBootApplication
public class GoShareApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoShareApplication.class, args);
	}

}