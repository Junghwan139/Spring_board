package com.example.Spring_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

// @EnableRedisHttpSession  // redis에 세션저장할 경우에 main 프로그램에 EnableRedisHttpSession 어노테이션 필요
@SpringBootApplication
public class SpringBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoardApplication.class, args);
	}

}
