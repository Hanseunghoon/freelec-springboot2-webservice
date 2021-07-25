package com.jojoldu.book.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    /*
        스프링 시큐리티에서 권한 코드는 반드시 "ROLE_XXXX" 형태로 ROLE이 앞에 들어가야 한다.
    */
    GUEST("ROLE_GUEST", "손님"), 
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
