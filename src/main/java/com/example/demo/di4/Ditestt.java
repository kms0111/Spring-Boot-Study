package com.example.demo.di4;

public class Ditestt {
    public static void main(String[] args) throws Exception {
        Acontext ac = new Acontext(conconfig.class);
        Fruit fruit = (Fruit) ac.getBean("fruit");
        System.out.println("수동 주입 전");
        System.out.println("fruit = " + fruit);
        System.out.println("--------------------------");
        System.out.println("수동 주입 후");
     /*   fruit.setSeed(new Seed());
        fruit.setSeason(new Season());*/
        System.out.println("fruit = " + fruit);

        System.out.println("자동주입 --------");
        Fruit fruit1 = ac.getBean(Fruit.class);
        System.out.println("fruit1 = " + fruit1);

    }




}
