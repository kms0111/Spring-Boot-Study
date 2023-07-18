package com.example.demo.aop;


import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AopMain {

    public static void main(String[] args) throws Exception{
        Class myClass =  Class.forName("com.example.demo.aop.MyClass");
        Object o=myClass.newInstance();

        MyAdvice myAdvice = new MyAdvice();

        for(Method m : myClass.getDeclaredMethods()){
            myAdvice.invoke(m, o , null);
        }
    }

}
class MyAdvice {

    Pattern p = Pattern.compile("a.*");

    boolean matches(Method m){
        Matcher matcher = p.matcher(m.getName());
        return matcher.matches();

    }


    void invoke(Method m , Object obj, Object ... args) throws Exception{

        if(m.getAnnotation(Transactional.class)!=null)
            System.out.println("[before]{"); // 공통으로 들어갈 코드

        m.invoke(obj, args); // aaa(), aaa2(), bbb() 호출

        if(m.getAnnotation(Transactional.class)!=null)
             System.out.println("}[after]"); // 공통으로 들어갈 코드
    }
}


class MyClass{
    @Transactional
    public void aaa(){
        System.out.println("aaa() is called.");
    }

    void aaa2(){
        System.out.println("aaa2() is called.");
    }

    void bbb(){
        System.out.println("bbb() is called.");
    }
}
