package com.example.demo.aop2;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AopMain {



    public static void main(String[] args) throws Exception {
        Class clazz = MyClass.class;
        MyClass o = (MyClass) clazz.newInstance();
        Method[] methods = clazz.getDeclaredMethods();
        MyAdvice myAdvice = new MyAdvice();


        for(Method m : methods){
            myAdvice.invoke(m,o,null);
        }


    }
}

class MyAdvice {

    Pattern pattern = Pattern.compile("a.*");

    public boolean matches(Method m){
        Matcher matcher = pattern.matcher(m.getName());

        return matcher.matches();

    }
    void invoke(Method m, Object obj, Object... args) throws Exception{
        if(m.getAnnotation(Transactional.class) != null)
            System.out.println("[before]{");

        m.invoke(obj, args);

        if(m.getAnnotation(Transactional.class) != null)
            System.out.println("}[after]");

    }
}




class MyClass {
    @Transactional
    public void aaa(){
        System.out.println("aaa() is calle");
    }

    void aaa1(){
        System.out.println("aaa1() is calle");
    }
    void bbb(){
        System.out.println("bbb() is calle");
    }
}