package com.example.springwebfluxdemo.reactor;

import reactor.core.publisher.Mono;

public class MonoFromSupplierRefactoring {
    public static void main(String[] args) {
        // you'll observe that only "Entered getName() method" is printed on the console
        // Because we are returning fromSupplier, and if we are not subscribed to this publisher then
        // it will not be consumed.
        getName();
        getName();

    }

    private static Mono<String> getName() {
        System.out.println("Entered getName() method");
        return Mono.fromSupplier(()->{
            // only when somebody subscribes to this, only then this block will be executed
            System.out.println("Generating name...");
            Util.sleepSeconds(3);
            return Util.faker().name().fullName();
        }).map(String::toUpperCase);
    }
}
