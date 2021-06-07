package com.gmail.gor;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try(var context= new AnnotationConfigApplicationContext(AppConfig.class)){
            var car = context.getBean((Car.class));
            car.start();

        }
    }
}
