package com.example.demo.di4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


//@Configuration : 해당 클래스 파일은 Bean을 등록하기 위한 java Config파일이라는 지정하는 어노테이션
@Configuration
@ComponentScan
public class ConfigTest {

    //@Bean 빈을 등록하는 어노테이션이며, 해당 빈은 key-value 형태로 SpringContext(ApplicationContext)등록된다.
    //이때 key는 매서드 이름(fruit)이고 value는 new Fruit()가 된다. key : value = fruit : new Fruit()
    //Scope를 지정해주지 않으면 기본적으로 singleton이다.
    @Bean
    public Fruit fruit(){
        return new Fruit();
    }

    //위와 동일하며 key-value형태로 저장된다. key는 메서드 이름이고, value 반환된 값이다. key : value = seed : new Seed()
    //@Scope를 prototype으로 지정되어 해당 객체는 singleton이 아닌 prototype이 된다.
    //따라서, 해당 Bean이 호출되면 새로운 객체가 생성된다.
    @Bean
    @Scope("prototype")
    public Seed seed(){
        return new Seed();
    }

    @Bean
    public Season season(){
        return new Season();
    }
}
