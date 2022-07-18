package com.example.springwebfluxdemo.reactor;

import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class MonoFromSupplierAndCallable {
    public static void main(String[] args) {
        Mono.just(getName()); // ideally nothing should happen as there are no subscribers
        // but we are seeing "Generating name" on the console. That means getName method got invoked,
        // and it is returning the name. So getName() method actually invoked because if the .just() method
        // and this is not right
        // TAKEAWAY: you can use just method only if you have data already. Otherwise, don't use .just()

        // so there are better ways to do this using Supplier and Callable
        // java 8 Supplier (no input, only output)
        Supplier<String> stringSupplier = () -> getName();
        Mono<String> mono = Mono.fromSupplier(stringSupplier);
        // now if you observe the output nothing happened. Because we are not subscribed to
        mono.subscribe(System.out::println);

        // using Callable
        Callable<String> stringCallable = () -> getName();
        Mono<String> monoCallable = Mono.fromCallable(stringCallable);
        monoCallable.subscribe(System.out::println);
    }

    private static String getName() {
        System.out.println("Generating name");
        return Util.faker().name().fullName();
    }
}
