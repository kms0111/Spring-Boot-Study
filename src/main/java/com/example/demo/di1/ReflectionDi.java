package com.example.demo.di1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionDi {

    public static void main(String[] args) throws Exception {
        
        
        Class aniamlClass1 = new Animal().getClass();
        Class animalClass2 = Class.forName("com.example.demo.di1.Animal");
        Class animalClass3 = Animal.class;    
        
        Method[] methods = aniamlClass1.getDeclaredMethods();
        System.out.println("Arrays.toString(methods) = " + Arrays.toString(methods));

        Method[] methods1 =animalClass2.getMethods();
        System.out.println("Arrays.toString(methods1) = " + Arrays.toString(methods1));

        Animal animal = (Animal) animalClass2.newInstance();
        Method method = aniamlClass1.getMethod("setName", String.class);
        method.invoke(animal, "나나");
        System.out.println("animal = " + animal);

        System.out.println("-----------------------------");
        Field[] fields = animalClass3.getDeclaredFields();
        System.out.println("Arrays.toString(fields) = " + Arrays.toString(fields));
        
        Field[] fields1 = animalClass3.getFields();
        System.out.println("Arrays.toString(fields1) = " + Arrays.toString(fields1));
        
        Annotation[] annotations = aniamlClass1.getAnnotations();
        System.out.println("Arrays.toString(annotations) = " + Arrays.toString(annotations));



    }

}
