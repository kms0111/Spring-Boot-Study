package com.example.demo.di1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionDi2 {


    public static void main(String[] args) throws Exception{


        //클래스 객체 만들기
        //1. 클래스명.class
        Class fruitClass1 = Fruit.class;

        //2. 생성자 .getClass()
        Class fruitClass2 = new Fruit().getClass();

        //3. Class.forName("패키지명.클래스명");
        Class fruitClass3 = Class.forName("com.example.demo.di1.Fruit");


        //매서드 배열 호출
        //1. Method[] 배열로 받는다.그리고 변수의 값으로 -> 클래스 객체.getDeclaredMethods();
        Method[] fristMethod = fruitClass1.getDeclaredMethods();
        //실행
        for(Method method : fristMethod){
            System.out.println("method = " + method);
        }

        //실제 매서드 실행
        //1. 객체생성
        Fruit fruit = (Fruit) fruitClass1.newInstance();
        //2. 매서드 뽑기: 클래스객체.getMethod("매서드 이름", "매개변수")
        Method fruitNameMethod = fruitClass1.getMethod("setName", String.class);
        //3. 매서드 실행 : 매서드.invoke(설정한 객체, 매개변수 값)
        fruitNameMethod.invoke(fruit,"열대과일");
        System.out.println(fruit.name); // 열대과일
        

        //클래스의 직접 지정한 필드 배열로 받기
        Field[] fruitFields =fruitClass1.getDeclaredFields();
        System.out.println("Arrays.toString(fruitFields) = " + Arrays.toString(fruitFields));

        //클래스의 public fleid만 가져오기
        Field[] fruitFields2 = fruitClass2.getFields();
        System.out.println("Arrays.toString(fruitFields2) = " + Arrays.toString(fruitFields2));


        //클래스의 필드마다에 어노테이션 꺼내기

        Fruit fruit1= new Fruit();

        for(Field field : fruitFields){
            Annotation[] annotations = field.getDeclaredAnnotations();
            for(Annotation annotation : annotations){
                if(annotation.annotationType() == Autowired.class){
                    String methodName = "set"+StringUtils.capitalize(field.getName());
                    Method method = fruitClass1.getMethod(methodName,String.class);
                    method.invoke(fruit1,"파파야");
                }
            }

        }
        System.out.println("fruit1.name = " + fruit1.name); // 파파야
    }

}
