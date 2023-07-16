package com.example.demo.di2;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AppContext {

    Map map = new HashMap();

    AppContext(){
        map.put("fruit", new Fruit());
        map.put("apple",new Apple());
    }

    AppContext(Class clazz) throws Exception {
        Object config = clazz.newInstance();

       Method[] methods=clazz.getDeclaredMethods();
       for(Method m : methods){
           Annotation[] annotations = m.getDeclaredAnnotations();
           for (Annotation annotation : annotations){
               if(annotation.annotationType() == Bean.class){
                   map.put(m.getName(),m.invoke(config,null));

               }
           }

       }
    }


    public Object getBean(String key) {
        return map.get(key);
    }

    public Object getBean(Class clazz) {

        for(Object obj : map.values()){
            if(clazz.isInstance(obj)){
                return obj;
            }
        }
        return null;



    }
}
