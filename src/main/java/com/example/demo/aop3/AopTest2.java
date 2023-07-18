package com.example.demo.aop3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public class AopTest2 {
    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(Config.class);
        MyMath myMath =  ac.getBean("myMath", MyMath.class);
        myMath.add(3,5);
        System.out.println("myMath.multiply(2,2) = " + myMath.multiply(2, 2));


    }
}
@Aspect
@Component
class LoggingAdvice{
    @Around("execution(* com.example.demo.aop3.MyMath.add*(..))")
    public Object methodClass(ProceedingJoinPoint pjp) throws Throwable {
        // target의 메서드 시작 부분에 추가될 코드
        long start = System.currentTimeMillis();
        System.out.println("<<[start]"+pjp.getSignature().getName()+ Arrays.toString(pjp.getArgs()));

        Object result = pjp.proceed(); // target 메서드를 호출한다.

        // target의 메서드 끝부분에 추가될 코드
        System.out.println("result = " + result);
        System.out.println("[end]>>"+(System.currentTimeMillis() - start)+"ms");
        return result;
    }
}

@EnableAspectJAutoProxy // AOP 자동 설정
@Configuration
@ComponentScan
class Config{

}

@Component
class MyMath{
    int add(int a, int b){
        int result = a + b;
        return result;
    }

    int add(int a, int b, int c){
        int result = a + b + c;
        return result;
    }

    int subtract(int a, int b){
        int result = a - b;
        return result;
    }

    int multiply(int a, int b){
        int result = a * b;
        return result;
    }
}
