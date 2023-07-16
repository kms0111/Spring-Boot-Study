package com.example.demo.di4;

import org.springframework.context.annotation.Bean;

public class conconfig {

    @Bean
    public Fruit fruit(){ // 메서드 이름이 빈의 이름이 됨
        return new Fruit();
    }

    @Bean
    public Season season(){
        return new Season();
    }

    @Bean
    public Seed seed() {
        return new Seed();
    }
}
