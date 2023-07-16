package com.example.demo.di5;



import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.util.Arrays;

class Car{
    @Override
    public String toString() {
        return "Car{}";
    }
}

class SportCar extends Car {
    @Override
    public String toString() {
        return "SportCar{}";
    }
}
class SportCar2 extends Car{
    @Override
    public String toString() {
        return "SportCar2{}";
    }
}

@Component
@Conditional(TrueCondition.class)
class Engine{
    @Override
    public String toString(){
        return "engine{}";
    }
}
@Component
@Conditional(OSCondition.class)
class Door{
    @Override
    public String toString() {
        return "Door{}";
    }
}



class TrueCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return true;
    }
}

class OSCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        System.out.println("System.getProperties() = " + System.getProperties());
        //return environment.getProperty("sun.desktop").equals("windows");
        return environment.getProperty("mode").equals("ss");

    }
}
//@Import({Config1.class, Config2.class})
@EnableMyAutoConfiguration("tesddt")
class MainConfig{//자바 설정 파일
    @Bean
    Car car1111(){ return new Car();}
}

class Config1{
    @Bean
    Car sportCar1(){
        return new SportCar();
    }
}

class Config2{
    @Bean
    Car sportCar2(){
        return new SportCar2();
    }
}
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportSelector.class)
@interface EnableMyAutoConfiguration{
    String value() default "";
}

class MyImportSelector implements ImportSelector{

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
       AnnotationAttributes attr =
               AnnotationAttributes
                       .fromMap(importingClassMetadata.getAnnotationAttributes(EnableMyAutoConfiguration.class.getName(),false));
        String mode = attr.getString("value");
        if(mode.equals("test"))
             return new String[]{Config1.class.getName()};
        else
            return new String[]{Config2.class.getName()};
    }
}

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Main {
    public static void main(String[] args) {
       // ApplicationContext ac = SpringApplication.run(Main.class,args); 여기서는 매개변수에 1개만 지정할 수 있음
       // ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class, Config1.class, Config2.class);
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);



        // 자바 설정을 이용하는 AC, 여러개의 설정파일을 매개변수에 넣을 수 있다.
        // 매개변수 안에 있는 class는 직접 다 지정해주었기 때문에 따로 @Configuration을 등록할 필요학 없다.
        String[] beanDefinitionNames=ac.getBeanDefinitionNames();

        Arrays.sort(beanDefinitionNames);
        Arrays.stream(beanDefinitionNames)
                .filter(b->!b.startsWith("org"))
                .forEach(System.out::println);
        System.out.println("ssssssssssssssssssssssss");
        System.out.println("Config1.class.getName() = " + Config1.class.getName());
        //System.out.println("ac.getBean(\"sportCar\") = " + ac.getBean("sportCar"));

    }
    @Bean
    MyBean myBean(){return new MyBean();}
}
class MyBean{}