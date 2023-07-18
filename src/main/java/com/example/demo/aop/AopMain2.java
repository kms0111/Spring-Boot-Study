package com.example.demo.aop;

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
@SpringBootApplication
@EnableAspectJAutoProxy // AOP 자동 설정
public class AopMain2 {
    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(AopMain2.class, args);
        MyMath mm = ac.getBean("myMath", MyMath.class);

        mm.add(3, 5);
        mm.add(1, 2, 3);
        System.out.println("mm.multiply(3,4) = " + mm.multiply(3, 4));
    }
}

@Aspect
@Component
class LoggingAdvice {
    @Around("execution(* com.example.demo.aop.MyMath.add*(..))")
    public Object methodClassLog(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("<<[start]" + pjp.getSignature() + Arrays.toString(pjp.getArgs()));

        Object result = pjp.proceed();

        System.out.println("result = " + result);
        System.out.println("[end]>> " + (System.currentTimeMillis() - start) + "ms");
        return result;
    }
}

@Component
class MyMath {
    int add(int a, int b) {
        int result = a + b;
        return result;
    }

    int add(int a, int b, int c) {
        int result = a + b + c;
        return result;
    }

    int subtract(int a, int b) {
        int result = a - b;
        return result;
    }

    int multiply(int a, int b) {
        int result = a * b;
        return result;
    }
}
