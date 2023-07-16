package com.example.demo.di3;
class Book{}

class ComicBook extends Book{}

class FantasyBook extends Book{}

public class Main {
    
    public static void main(String[] args) throws Exception {
        AppContext appContext = new AppContext(AppConfig.class);
        Book book = (Book) appContext.getBean(Book.class);
        System.out.println("book = " + book);

        AppContext appContext1 = new AppContext();
        Book book1= (Book) appContext1.getBean("comicBook");
        System.out.println("book1 = " + book1);
        Book book2 = (Book) appContext1.getBean("FantasyBook");
        System.out.println("book2 = " + book2);
    }
}

