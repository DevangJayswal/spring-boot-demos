package com.example.springwebfluxdemo.redis;

import com.example.springwebfluxdemo.redis.model.Coffee;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.UUID;

//@Component
public class CoffeeLoader {
    private final ReactiveRedisConnectionFactory factory;
    private final ReactiveRedisOperations<String, Coffee> coffeeOps;

    public CoffeeLoader(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String, Coffee> coffeeOps) {
        this.factory = factory;
        this.coffeeOps = coffeeOps;
    }

    @PostConstruct
    public void loadData() {
        //load coffees in redis
        factory.getReactiveConnection().serverCommands().flushAll().thenMany(
                        Flux.just("Jet Black Redis", "Darth Redis", "Black Alert Redis")
                                .map(name -> new Coffee(UUID.randomUUID().toString(), name))
                                .flatMap(coffee -> coffeeOps.opsForValue().set(coffee.getId(), coffee)))
                .thenMany(coffeeOps.keys("*")
                        .flatMap(coffeeOps.opsForValue()::get))
                .subscribe(System.out::println)
        ;


        // get coffee by key
//        Mono<Coffee> coffeeMono = coffeeOps.opsForValue()
//                .get("272569b1-9e18-4fd6-8755-a7c6afdcdcde");

        // if coffee by key is present in redis
//        coffeeMono.subscribe(coffee -> {
//            System.out.println("\nCoffee: " + coffee.getName());
//        });


        // if coffee by key is not present in redis
//        coffeeMono.switchIfEmpty(Mono.fromRunnable(() -> {
//            System.out.println("\n Coffee not found in redis\n");
//        })).subscribe();

        filter();


//        coffeeOps.keys("*")
//                .flatMap(coffeeOps.opsForValue()::get)
//                .subscribe(coffee -> {
//                    System.out.println("Coffee Name: " + coffee.getName());
//                });

//        coffeeOps.opsForValue()
//                .get("c8fa67de-9859-4576-93b9-0b38ee46ae0f")
//                .subscribe(coffee -> {
//                    System.out.println("\nCoffee Name: "+ coffee.getName());
//                });
    }

    public Mono<Void> filter() {

        Mono<Coffee> coffeeMono = coffeeOps.opsForValue()
                .get("272569b1-9e18-4fd6-8755-a7c6afdcdcde");

        // if coffee by key is present in redis
        coffeeMono.subscribe(coffee -> {
            System.out.println("\nCoffee: " + coffee.getName());
        });

        coffeeMono.switchIfEmpty(Mono.fromRunnable(() -> {
                    System.out.println("--- Coffee is not present in redis");
                })).then(Mono.empty())
                .flatMap(o -> Mono.empty())
                .subscribe(System.out::println)
        ;


        return Mono.empty();
    }
}