package com.jojoldu.book.springboot.domain.posts;

import lombok.*;

import javax.persistence.*;

/*
    @Entity
    테이블과 링크될 클래스임을 명시
    기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭
    ex) SalesManager.java -> sales_mangaer table
 */
@Getter
@NoArgsConstructor
@Entity
public class Posts {

    /*
        @Id
        해당 테이블의 PK 필드

        @GeneratedValue
        PK 생성 규칙
        스프링 부트 2.0 -> GenerationType.IDENTITIY 옵션을 반드시 추가해야만 auto_increment가 됨
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
        @Column
        테이블의 칼럼
        굳이 선언 안해도 해당 클래스의 필드는 모두 컬럼이지만 해주는게 보기 좋을지도
        기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
        문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나, 타입을 TEXT로 변경하고 싶거나 등의 경우에 사용
    */
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    /*
        @Builder 
        해당 클래스의 빌더 패턴 클래스 생성
        생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    */
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
