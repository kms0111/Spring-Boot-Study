package com.example.demo.di2;

import org.springframework.context.annotation.Bean;

public class AppConfig {

    @Bean public Animal animal(){ //메서드 이름이 빈의 이름
        Animal animal = new Animal();
        return animal;
    }

}
