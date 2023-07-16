package com.example.demo.di1;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

public class testObject {





}
class Fruit{
    @Autowired
    public String name;
    String color;

    public void setName(String name){
        this.name=name;
    }

    public void setColor(String color){
        this.color=color;
    }



}
@Data
class Apple extends Fruit{

    String age;






}
@Data
class Banana extends  Fruit{

    String homeTown;
}
