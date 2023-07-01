package com.sparta.plug.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig { // passwordConfig 형태로 Bean에 등록된다.

    @Bean  // Bean의 수동 등록 -> 스프링 서버가 실행 될때 spring ioc container에 bean으로 저장된다.
    public PasswordEncoder passwordEncoder() { //PasswordEncoder는 interface이기 때문에 구현체를 만들어 줘야한다.

        // --> 구현체, BCrypt는 해쉬 함수로서 비밀 번호를 암호화 해주는 것들중에서 아주 강력한 해쉬 매커니즘을 가지고 있다.
        return new BCryptPasswordEncoder();

    }
}