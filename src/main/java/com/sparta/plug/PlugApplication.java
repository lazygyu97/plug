package com.sparta.plug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PlugApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlugApplication.class, args);
    }

}
