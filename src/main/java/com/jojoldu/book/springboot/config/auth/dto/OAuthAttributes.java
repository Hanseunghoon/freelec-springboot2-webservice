package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.Role;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributesKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributesKey, String name, String email, String picture){
        this.attributes = attributes;
        this.nameAttributesKey = nameAttributesKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를 변환하기 위한 함수
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributesKey(userNameAttributeName)
                .build();
    }

    // User 엔티티 생성 -> OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)   // 가입할 때 기본 권한을 GUSET로 줌
                .build();
    }
}