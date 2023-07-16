package com.example.demo.di1;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Data
class Animal{


    String name;
    public  int speed;


    public void setName(String name){
        this.name=name;
    }

}
class Cat extends Animal{}
class Dog extends Animal{}

public class diTest {

    public static void main(String[] args) throws Exception {

        //이렇게 하면 직접 new Cat을 하지 않고도 매서드만 호출하여 생성할 수 있음
        Animal animal = getAnimal();
        Animal animal1 =getAnimal();
        Animal animal2 =getAnimal();
        //만약 getAnimal을 수정하고 싶으면 매서드의 return값을 변경만 하면 일괄적으로 적용된다.
        //Animal이라는 조상이 있기 때문에 getAnimal()매서드는 다형성이 적용되어 Animal을 조상으로 가지고 있는 클래스는 모두 적용된다.
        System.out.println("다형성 매서드 1");
        System.out.println("animal = " + animal);
        System.out.println("animal1 = " + animal1);
        System.out.println("animal2 = " + animal2);

        System.out.println("다형성 메서드 2");
        Animal animal3=(Animal) getObjectProPerties("dog");
        System.out.println("animal3 = " + animal3);


        System.out.println("다형성 메서드 3");
        Animal animal4 = (Animal) getObjectProPerties("animal");
        Dog dog=(Dog) getObjectProPerties("dog");
        Cat cat=(Cat) getObjectProPerties("cat");
        System.out.println("dog = " + dog);
        System.out.println("cat = " + cat);
        System.out.println("animal3 = " + animal3);
    }
    static Animal getAnimal(){
        return new Dog();
    }

    static Object getObjectKey(String key){
        if(key.equals("dog")) return new Dog();
        else if (key.equals("cat")) return new Cat();
        else return new Animal();
    }

    static Object getObjectProPerties(String key) throws Exception {
        Properties prop = new Properties();
        Class clazz= null;
            prop.load(new FileReader("config.txt"));
            String className = prop.getProperty(key);
            clazz = Class.forName(className);
        return clazz.newInstance();
    }
}
