package com.example.springwebfluxdemo.reactor;

import com.github.javafaker.Faker;

import java.util.function.Consumer;

public class Util {
    private static final Faker FAKER = Faker.instance();

    public static Consumer<Object> onNext() {
        return object -> System.out.println("RECEIVED: " + object);
    }

    public static Consumer<Throwable> onError() {
        return error -> System.out.println("ERROR: " + error.getMessage());
    }

    public static Runnable onComplete() {
        return () -> System.out.println("COMPLETED");
    }

    public static Faker faker() {
        return FAKER;
    }

    public static void sleepSeconds(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
