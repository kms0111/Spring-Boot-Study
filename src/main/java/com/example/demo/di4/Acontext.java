package com.example.demo.di4;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Acontext {

    Map map = new HashMap();

    Acontext(){

    }

    Acontext(Class clazz) throws Exception{
        Object obj = clazz.newInstance();
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods){
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations){
                if(annotation.annotationType() == Bean.class){
                    map.put(method.getName(),method.invoke(obj,null));
                }
            }
        }
        doAutowired();
        doResource();
    }

    private void doResource() throws IllegalAccessException {
        for(Object bean : map.values()){
            Field[] fields = bean.getClass().getDeclaredFields();
            for(Field field : fields){
                if(field.getAnnotation(Resource.class) != null){
                    field.set(bean, getBean(field.getName()));
                }
            }
        }
    }

    public Object getBean(String key){
        return map.get(key);
    }
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> clazz) {
        for (Object obj : map.values()) {
            if (clazz.isInstance(obj)) {
                return clazz.cast(obj);
            }
        }
        return null;
    }

    private void doAutowired() throws IllegalAccessException {
        for(Object bean : map.values()){
            Field[] fields = bean.getClass().getDeclaredFields();
            for(Field field : fields){
                if(field.getAnnotation(Autowired.class) != null){
                    field.set(bean, getBean(field.getType()));
                }
            }
        }
    }
}
