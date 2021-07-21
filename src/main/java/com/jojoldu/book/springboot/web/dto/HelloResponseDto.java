package com.jojoldu.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
    1. @Getter
    선언된 모든 필드의 get___ 메소드 생성

    2. @RequiredArgsConstructor
    선언된 모든 final 필드가 포함된 생성자 생성
    final이 없는 필드는 생성자 생성 x
 */
@Getter
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
