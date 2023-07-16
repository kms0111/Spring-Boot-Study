package com.example.demo.di3;

import org.springframework.context.annotation.Bean;

public class AppConfig {


    @Bean
    public Book book(){
        return new Book();
    }


}
