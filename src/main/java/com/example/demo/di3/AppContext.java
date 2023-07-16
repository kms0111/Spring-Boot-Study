package com.example.demo.di3;

import org.springframework.context.annotation.Bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AppContext {
    Map map = new HashMap();

    AppContext(){
        map.put("comicBook", new ComicBook());
        map.put("FantasyBook", new FantasyBook());
    }

    AppContext(Class clazz) throws Exception {
        Object obj = clazz.newInstance();
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods){
            Annotation[] anno = method.getDeclaredAnnotations();
            for(Annotation ano : anno){
                if(ano.annotationType() == Bean.class){
                    map.put(method.getName(),method.invoke(obj,null));
                }
            }
        }
    }

    //이름으로 찾는다. 즉 @Resources
    public Object getBean(String key){
        return map.get(key);
    }

    //타입으로 찾는다. @Autowired
    public Object getBean(Class clazz){
        for(Object obj : map.values()){
            if(clazz.isInstance(obj)){
                return obj;
            }
        }
        return null;
    }

}
