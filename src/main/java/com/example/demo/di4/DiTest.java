package com.example.demo.di4;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;
@Data
class Fruit{
    @Autowired Seed seed;
    @Resource Season season;

    @Override
    public String toString() {
        return "Fruit{" +
                "seed=" + seed +
                ", season=" + season +
                '}';
    }
}
class Seed{}
class Season{}

public class DiTest {
    public static void main(String[] args) {
        /*
         * new AnnotationConfigApplicationContext의 조상은 ApplicationContext라서 다형성이 적용되어
         * 아래와 같이 변수를 조상인 ApplicationContext라고 정하고 해당 값을 자식을 new AnnotationConfigApplicationContext를 지정할 수 있다.
         * AnnotationConifgApplicationContext의 매개변수에는 @Bean 등록된 config 클래스파일이 올 수 있다.
         * 이 다음 변수명 ac를 통해 Bean들을 상태를 확인해볼 수 있는 메서드를 실행해 볼 수 있다.
         * */
        ApplicationContext ac = new AnnotationConfigApplicationContext(ConfigTest.class);

        /*
        * getBean(이름)을 통해 Fruit 클래스의 생성자를 주입한다. 반환티입은 Object이기에 형변환을 해주어야 한다(byName 기준)
        * 이때 매개변수에는 매서드의 이름이 들어간다.
        * */
        Fruit fruit = (Fruit) ac.getBean("fruit");
        System.out.println("fruit = " + fruit);

        /*
        * ac.getBean(클래스명)은 오버로딩이 되어있어서 매개변수에 클래스가 올 수 있다. byType 기준으로 해당 Bean의 매서드를 찾아 주입한다.
        * class는 따로 항변환을 안해주어도 된다(어떤 클래스이기 알기에..)
        * */
        Fruit fruit1 = ac.getBean(Fruit.class);
        System.out.println("fruit1 = " + fruit1);

        /*
        * 위와 동일한 매서드이다(즉, Bean객체를 불러오는 오버로딩) byName and byType
        * 매개 변수로는 매서드 이름과 해당 메서드의 반환타입을 클래스명으로 적어주면 된다.
        * */
        Fruit fruit2=ac.getBean("fruit",Fruit.class);
        System.out.println("fruit2 = " + fruit2);

        // getBeanDefinitionNames는 SpringContext에 Bean으로 등록 객체들의 이름(패키치명+클래스명) String 배열로 반환한다.
        String[] beanNames = ac.getBeanDefinitionNames();
        System.out.println("Arrays.toString(beanNames) = " + Arrays.toString(beanNames));

        // getBeanDefinitionCount는 SpringContext에서 Bean으로 등록된 객체의 수를 반환한다.
        int beanCount = ac.getBeanDefinitionCount();
        System.out.println("beanCount = " + beanCount);

        //기본적으로 singleton이 디폴트이기 때문에 true로 나온다.
        //isSington(이름) byName 해당 bean이 singleton인지 체크한다.
        boolean singletonCheck = ac.isSingleton("season");
        System.out.println("singletonCheck = " + singletonCheck);//true

        //isprototype(이름) byName 해당 bean이 prototype인지 체크한다.
        boolean prototypeCheck1 = ac.isPrototype("season");
        System.out.println("prototypeCheck1 = " + prototypeCheck1);//false

        //싱글톤은 객체 1개를 다 공유하는 형태이기 때문에 새로운 객체를 만들어내도 반환하면 똑같은 주소값을 반환한다.
        Fruit fruit3 = ac.getBean("fruit",Fruit.class);
        Fruit fruit4 = ac.getBean("fruit",Fruit.class);
        Fruit fruit5 = ac.getBean("fruit",Fruit.class);

        System.out.println("fruit3 = " + fruit3.hashCode()); //983595261
        System.out.println("fruit4 = " + fruit4.hashCode()); //983595261
        System.out.println("fruit5 = " + fruit5.hashCode()); //983595261


        //singleton을 prototype으로 바꿔서 출력할 수 있다(해당 작업은 @Bean등록하는 클래스에서 @Scope("prototype")으로 바꿔줌
        boolean prototypeCheck2 = ac.isPrototype("seed");
        System.out.println("prototypeCheck = " + prototypeCheck2); // true

        /*
        * prototype은 singleton과 달리 SpringContext에서 bean을 호출할때마다 새로운 객체를 호출한다.
        * 따라서, 객체를 공유하지 않고 새로운 객체가 생성됨으로 호출할때 메모리 부담도 있고
        * 일관적인 서비스를 하는 웹에서는 사용할때 고려를 해봐야하는 타입이다.
        * 아래의 코드는 prototype으로 SpringContext에 지정된 Bean 여러개 호출한 코드이다.
        * */

        Seed seed=(Seed) ac.getBean("seed");//byName 이름으로 bean 찾기
        Seed seed1 = ac.getBean("seed", Seed.class); //byName and byType 으로 bean 찾기
        Seed seed2 = ac.getBean(Seed.class); //byType 클래스로 bean 찾기

        System.out.println("seed = " + seed.hashCode());  //575935098
        System.out.println("seed1 = " + seed1.hashCode());//1367937032
        System.out.println("seed2 = " + seed2.hashCode());//798310141
        //해시코드 주소가 다 다르다.

        //containBeanDefinition은 해당 byName으로 검색해서 해당 Bean이 있는지 boolean 타입으로 반환해준다.
        System.out.println(ac.containsBeanDefinition("seed"));  //true
        System.out.println(ac.containsBeanDefinition("fruit")); //true
        System.out.println(ac.containsBeanDefinition("season"));//true
        System.out.println(ac.containsBeanDefinition("asdfawe111"));//false

        System.out.println("---------------------------------");

        SysInfo sysInfo = ac.getBean(SysInfo.class);
        System.out.println("sysInfo = " + sysInfo);




    }
}
