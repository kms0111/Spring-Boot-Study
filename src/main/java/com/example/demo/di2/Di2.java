package com.example.demo.di2;

import lombok.Data;

public class Di2 {
    public static void main(String[] args) throws Exception {
        AppContext ac = new AppContext(AppConfig.class);
        Fruit fruit = (Fruit) ac.getBean(Fruit.class);
        System.out.println("fruit = " + fruit);
        Animal animal=(Animal) ac.getBean("animal");
        System.out.println("animal = " + animal);
    }
}
@Data
class Animal{

    String name;
    int age;

}

@Data
class Apple{}
@Data
class Fruit{}