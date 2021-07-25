package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    
    /*
        인증된 사용자 정보만 필요. 그 외 필요한 정보들은 없어 name, email, picture 필드만 사용
        
        * 따로 SessionUser를 만든 이유는?
        User클래스는 직렬화를 하지 않아 세션에 저장할 수 없다. 에러 발생.
        엔티티이므로 직렬화를 하지 않았다. 엔티티 클래스는 언제 다른 엔티티와 관계를 형성할지 모르기 때문
        따라서 직렬화 기능을 가진 세션 DTO를 추가로 만들면 성능 이슈나 부수 효과 줄여 유지보수에 좋음
    */

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
