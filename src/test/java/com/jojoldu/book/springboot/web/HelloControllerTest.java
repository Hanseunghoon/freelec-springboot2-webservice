package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*  1. @RunWith(SpringRunner.class)
    테스트 진행 시 JUnit에 내장된 실행자 외에 다른 실행자를 실행
    SpringRunner라는 스프링 실행자 사용
        즉, 스프링 부트 테스트와 JUnit 사이 연결자 역할

    2. @WebMvcTest
    여러 스프링 테스트 어노테이션 중 Web에 집중할 수 있는 어노테이션
    선언 시 @Controller, @ControllerAdvice 등 사용 o
           @Service, @Component, @Repository 등 사용 x
    여기서는 컨트롤러만 사용하기 때문에 선언
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    /* 
        3. @Autowired
        스프링이 관리하는 빈(Bean)을 자동으로 주입 받음
        
        4. private MockMvc mvc
        웹 API를 테스트할 때 사용
        스프링 MVC 테스트 시작점
        이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트 수행 가능
    */
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";
        /*
            5. mvc.perform(get("/hello"))
            MockMvc를 통해 /hello 주소로 HTTP GET 요청
            체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언 가능 (.andExpect)

            6. .andExpect(status().isOk())
            mvc.perform의 결과를 검증
            HTTP Header의 Status를 검증
            우리가 흔히 알고 있는 200, 404, 500 등의 상태를 검증
            OK (200) 인지 아닌지를 검증

            7. .andExpect(content().string(hello))
            mvc.perform의 결과를 검증
            응답 본문의 내용 검증
            Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
        */
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        /*
            1. param
            API 테스트할 때 사용될 요청 파라미터 설정 (단, 값은 String만 허용)
            숫자/날짜 등 데이터도 등록 시 문자열로 변경 필수

            2. jsonPath
            JSON 응답값을 필드별로 검증할 수 있는 메소드
            $를 기준으로 필드명을 명시 (name과 amount를 검증하므로, $.name, $.amount로 검증)
         */
        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}