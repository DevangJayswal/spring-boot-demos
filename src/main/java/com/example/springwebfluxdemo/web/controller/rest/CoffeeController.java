package com.example.springwebfluxdemo.web.controller.rest;

import com.example.springwebfluxdemo.redis.model.Coffee;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
public class CoffeeController {
//    private final ReactiveRedisOperations<String, Coffee> coffeeOps;
//
//    CoffeeController(ReactiveRedisOperations<String, Coffee> coffeeOps) {
//        this.coffeeOps = coffeeOps;
//    }

//    @GetMapping("/coffees")
//    public Flux<Coffee> all() {
//        System.out.println("Hello");
//
//
//        return coffeeOps.keys("*")
//                .flatMap(coffeeOps.opsForValue()::get);
//    }

    @GetMapping
    public Mono<String> all() {
        return Mono.just("hello");
    }
}