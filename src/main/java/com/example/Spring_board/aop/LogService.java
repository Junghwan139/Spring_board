package com.example.Spring_board.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect

// Component를 통해 Spring bean객체를 만드는 것(bean객체는 싱글톤을 의미)
@Component

// 로그 라이브러리 log4J 라이브러리 사용
@Slf4j
public class LogService {

    // Around 어노테이션을 통해 logging의 대상을 지정
    @Around("execution(* com.example.Spring_board..controller..*.*(..))")
    public Object controllerLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // 로그의 형태를 json으로 출력하기 위해 json 객체 생성
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("method name", proceedingJoinPoint.getSignature().getName());

        // 사용자의 request정보는 HttpServletRequest객체 안에 담겨 있으므로, 해당 객체에서 추출
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        jsonObject.put("CRUD name", req.getMethod());  // get, put 요청
        Enumeration<String> req_body = req.getParameterNames();

        JSONObject jsonObject_detail = new JSONObject();

        while(req_body.hasMoreElements()){
            String body = req_body.nextElement();
            jsonObject_detail.put(body, req.getParameter(body));

        }
        jsonObject.put("user inputs",jsonObject_detail);
        // log4j 라이브러리를 통해 위해서 만든 jsonObject를 log로 출력
        // 일반적으로 실무에서는 콘솔이 아니라, log를 파일로 영구저장되도록 관리한다.
        // 그런데, 정상적인 request까지 모두 log로 남기게 되면 파일시스템에 용량이 full차는 경우가 빈번.
        // 그래서 log.info 뿐만 아니라 log.trace, log.debug, log.info, log.error 등의 로그레벨이 존재한다.
        // 에러가 발생했을 때만 아래 log가 출력된다.

//        log.debug("안찍힘");
//        log.error("찍힘");
//        log.info("request info"+jsonObject);

        return proceedingJoinPoint.proceed();







    }

}
