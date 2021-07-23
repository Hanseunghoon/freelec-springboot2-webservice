package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  // JPA Auditing 활성화
@SpringBootApplication
public class Application {
    /*
        Application : 프로젝트의 메인 클래스
            @SpringBootApplication : 스프링 부트의 자동 설정, 스프링 Bean 읽기/생성 모두 자동 설정
                                     해당 어노테이션이 있는 위치부터 설정을 읽음 > 항상 프로젝트 최상단에 존재!
     */
    public static void main(String[] args) {
        // 내장 WAS를 실행 (별도로 외부에 WAS를 두지 않고 애플리케이션 실행 시 내부 WAS를 실행
        // 항상 서버에 톰캣을 설치할 필요 X
        SpringApplication.run(Application.class, args);
    }
}