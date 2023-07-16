package com.example.demo.di4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration//일반 클래스가 아닌 설정 클래스라는 거임. 객체가 아닌 설정을 위한 클래스라는 것임
// AC생성하면서 @Bean으로 설정된것을 등록한다.
public class AppConfig {
    @Bean
    Car car(){
        return new Car();
    }
    @Bean
    Engine engine(){
        return new Engine();
    }
    @Bean
    Door door(){
        return new Door();
    }
}
