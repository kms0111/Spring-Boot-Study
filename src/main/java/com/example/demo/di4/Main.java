package com.example.demo.di4;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
class Car{
    Engine engine;
    Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}

class Engine{}
class Door{}


public class Main {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //Car car =(Car)ac.getBean("car"); // Car를 byName 객체(빈)을 조회
        Car car= ac.getBean("car", Car.class);//위와 동일하나 항변환을 안해도 된다.
        System.out.println("car = " + car);
        Engine engine=ac.getBean(Engine.class);//byType으로 가져오기
        Engine engine1=ac.getBean(Engine.class);
        Engine engine2=ac.getBean(Engine.class);


        System.out.println("engine = " + engine);
        System.out.println("engine1 = " + engine1);
        System.out.println("engine2 = " + engine2);

        System.out.println("ac.getBeanDefinitionCount() = " + ac.getBeanDefinitionCount());
        System.out.println("ac.getBeanDefinitionNames() = " + Arrays.toString(ac.getBeanDefinitionNames()));
        //메서드 이름이 key로 저장
        System.out.println("ac.containsBeanDefinition(\"engine\") = " + ac.containsBeanDefinition("engine"));
        System.out.println("ac.isSingleton(\"engine\") = " + ac.isSingleton("engine"));
        System.out.println("ac.isPrototype(\"engine\") = " + ac.isPrototype("engine"));
        System.out.println("ac.getBean(\"door\") = " + ac.getBean("door"));
        System.out.println("ac.getBean(\"engine\") = " + ac.getBean("engine"));


    }

}
