package com.example.Spring_board.author.service;


import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        로그인에 성공했을때, 추가적인 기능을 구현하고 싶다면 이 메서드에 구현
        // 로그인 성공하면 세션이 request값에 저장됨 따라서 꺼낼 수 있음
        HttpSession session = request.getSession();
        session.setAttribute("my_greeting",authentication.getName() + "님 반갑습니다.");

        response.sendRedirect("/");   // 로그인 성공시 홈화면으로 튕겨주기

    }
}
