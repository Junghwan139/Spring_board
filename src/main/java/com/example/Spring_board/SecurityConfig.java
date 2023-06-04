package com.example.Spring_board;

import com.example.Spring_board.author.service.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// EnableWebSecurity 어노테이션은 spring security를 customizing 할 수 있는 기능 활성화
@EnableWebSecurity
public class SecurityConfig {

    // spring에서 bean을 만드는 방법 2가지(싱글톤)
    // 방법 1. Component 방식
    // 개발자가 직접 컨트롤이 가능한 내부 클래스에서 사용

    // 방법 2. Configuration + Bean 방식
    // 개발자가 컨트롤이 불가능한 외부 라이브러리 적용시 사용


    // * 싱글톤(controller, service, repository 등 componet, configuration+bean) 기능만(변수) 하기 때문에 돌려써도 상관 없음
    //   싱글톤x(author, post domain 객체) 고유한 값을 가지고 있어야 하는 성격은 그때 그때 객체를 만들어줘야 함. 사용자가 1000명이 들어오면 개별적으로 가지고 있어야함.

    @Bean
    public SecurityFilterChain myFilter(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                // 보안공격에 대한 설정은 별도로 하지 않겠다는 뜻
                .csrf().disable()
                .authorizeRequests()
                // login authenticated에서 제외대는 대상 url 지정
                .antMatchers("/author/login","/","/authors/new")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/author/login")
                // spring의 doLogin 기능 그대로 활용
                    .loginProcessingUrl("/doLogin")
                    .usernameParameter("email")
                    .passwordParameter("password")
                // 로그인 성공시 이후 로직 LoginSucessHandler에서 구현
                    .successHandler(new LoginSuccessHandler())
                .and()
                .logout()
                // spring의 doLogout 기능 그대로 활용
                   .logoutUrl("/doLogout")
                .and().build();

    }



}
