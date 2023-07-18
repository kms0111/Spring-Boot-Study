package com.example.demo.aop3;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AopTest {
    public static void main(String[] args) throws Exception{
        Class welcomeClass = new Welcome().getClass();
        Welcome welcome = (Welcome) welcomeClass.newInstance();

        MyAdvice myAdvice = new MyAdvice();

        Method[] methods = welcomeClass.getDeclaredMethods();

        for(Method m : methods){
            myAdvice.logFirtstTime(m,welcome,null);
        }
    }
}

class MyAdvice {

    Pattern pattern = Pattern.compile("f.*");

    boolean matchesName(Method method) {
        Matcher matcher = pattern.matcher(method.getName());

        return matcher.matches();
    }

    boolean annotationCheck(Method m){
        if(m.getAnnotation(Transactional.class) != null){
            return true;
        }
        return false;
    }
    void logFirtstTime(Method m, Object obj, Object ... args) throws Exception{
        if(annotationCheck(m))
            System.out.println(m.getName() +": 접속한 시간. " + System.currentTimeMillis());

        m.invoke(obj, args);

        if(annotationCheck(m))
            System.out.println();
    }
}

class Welcome{

    @Transactional
    public void cat(){
        System.out.println("cat이 접속했습니다");
    }
    void dog(){
        System.out.println("dog이 접속했습니다");
    }
    void frog(){
        System.out.println("frog이 접속했습니다");
    }
}
